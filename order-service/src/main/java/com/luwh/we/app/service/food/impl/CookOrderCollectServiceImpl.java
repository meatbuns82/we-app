package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.enums.CookOrderTypeEnums;
import com.luwh.we.app.common.exception.exceptions.OrderException;
import com.luwh.we.app.dao.food.UserCookCollectRelationInfoDao;
import com.luwh.we.app.dao.food.UserCookCollectRelationInfoRepository;
import com.luwh.we.app.dto.response.CookCollectResponse;
import com.luwh.we.app.dto.response.FoodDetailOverviewResponse;
import com.luwh.we.app.model.po.food.UserCookCollectRelationInfoPO;
import com.luwh.we.app.service.food.CookOrderCollectService;
import com.luwh.we.app.service.food.FoodAndCookOverviewService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lu.wh
 * @date 2023/12/04 14/50/21
 * @description
 */
@Service
public class CookOrderCollectServiceImpl extends ServiceImpl<UserCookCollectRelationInfoDao, UserCookCollectRelationInfoPO> implements CookOrderCollectService {

    @Resource
    private FoodAndCookOverviewService foodAndCookOverviewService;
    @Resource
    private UserCookCollectRelationInfoRepository repository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void goodCook(String cookCode, String account) {
        // 一个用户只能点赞一次， 如果点赞会把点踩取消
        if (checkHasGood(cookCode, account)) {
            return;
        }
        cancel(cookCode, account, 1);
        UserCookCollectRelationInfoPO po = new UserCookCollectRelationInfoPO(account, cookCode, 0);
        repository.add(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void badCook(String cookCode, String account) {
        // 一个用户只能点踩一次，如果点踩会把点赞取消
        if (checkHasBad(cookCode, account)) {
            return;
        }
        cancel(cookCode, account, 0);
        UserCookCollectRelationInfoPO po = new UserCookCollectRelationInfoPO(account, cookCode, 1);
        repository.add(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void collectCook(String cookCode, String account, boolean collect) {
        // 一个用户只能收藏一次，同时会点赞
        if (checkHasCollect(cookCode, account)) {
            // Repeat collect will to discard collect latest
            discardCollect(cookCode, account);
        }
        if(collect) {
            // 点赞，收藏
            ((CookOrderCollectService) AopContext.currentProxy()).goodCook(cookCode, account);
            UserCookCollectRelationInfoPO po = new UserCookCollectRelationInfoPO(account, cookCode, 2);
            repository.add(po);
        }else {
            // 取消收藏
            discardCollect(cookCode, account);
        }
    }

    private void discardCollect(String cookCode, String account){
        // 取消收藏
        ((CookOrderCollectService) AopContext.currentProxy()).badCook(cookCode, account);
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getAccount, account)
                .eq(UserCookCollectRelationInfoPO::getCookCode, cookCode);
        repository.delete(wrapper);
    }

    @Override
    public Map<String, Map<String, Integer>> countTypeByCookCodes(List<String> cookCodes) {
        if (CollectionUtils.isEmpty(cookCodes)) {
            return new HashMap<>();
        }
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.in(UserCookCollectRelationInfoPO::getCookCode, cookCodes);
        List<UserCookCollectRelationInfoPO> infoPOS = repository.list(wrapper);
        // Classification by cookCode
        return classification(infoPOS);
    }

    @Override
    public  Page<CookCollectResponse> collectList(String account, Integer page, Integer size) {
        // account exist check
        if (!StringUtils.hasText(account)) {
            throw new OrderException("Absent account");
        }
        // TODO account disable check

        // list collect info by account
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getAccount, account);
        List<UserCookCollectRelationInfoPO> infoPOS = repository.list(wrapper);
        List<String> cookCodes = infoPOS.stream().map(e -> e.getCookCode()).collect(Collectors.toList());

        Page<FoodDetailOverviewResponse> overviewResponsePage =
                foodAndCookOverviewService.selectFoodDetailOverviewByCookCode(page, size, cookCodes);
        List<CookCollectResponse> collect = overviewResponsePage.getRecords().stream().map(
                e -> CookCollectResponse.fromPageFoodDetailOverviewResponse(e, account)
        ).collect(Collectors.toList());
        Page<CookCollectResponse> response = new Page<>(overviewResponsePage.getCurrent(), overviewResponsePage.getSize());
        response.setRecords(collect);
        return response;
    }

    /**
     * 删除指定的数据
     *
     * @param cookCode
     * @param account
     * @param type
     */
    private void cancel(String cookCode, String account, Integer type) {
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getCookCode, cookCode)
                .eq(UserCookCollectRelationInfoPO::getAccount, account)
                .eq(UserCookCollectRelationInfoPO::getType, type);
        repository.delete(wrapper);
    }

    private boolean checkHasGood(String cookCode, String account) {
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getCookCode, cookCode)
                .eq(UserCookCollectRelationInfoPO::getAccount, account)
                .eq(UserCookCollectRelationInfoPO::getType, 0);
        UserCookCollectRelationInfoPO result = repository.get(wrapper);
        return result != null;
    }

    private boolean checkHasBad(String cookCode, String account) {
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getCookCode, cookCode)
                .eq(UserCookCollectRelationInfoPO::getAccount, account)
                .eq(UserCookCollectRelationInfoPO::getType, 1);
        UserCookCollectRelationInfoPO result = repository.get(wrapper);
        return result != null;
    }

    private boolean checkHasCollect(String cookCode, String account) {
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getCookCode, cookCode)
                .eq(UserCookCollectRelationInfoPO::getAccount, account)
                .eq(UserCookCollectRelationInfoPO::getType, 2);
        UserCookCollectRelationInfoPO result = repository.get(wrapper);
        return result != null;
    }

    /**
     * Classification for cook according cookCode
     * @param infoPOS
     * @return
     */
    private Map<String, Map<String, Integer>> classification(List<UserCookCollectRelationInfoPO> infoPOS){
        Map<String, Map<String, Integer>> map = new HashMap<>();
        infoPOS.stream().forEach(e -> {
            // 每个cookCode里按照 不同的type分类好
            CookOrderTypeEnums cookOrderType = CookOrderTypeEnums.fromVal(e.getType());
            Map<String, Integer> countMap = map.get(e.getCookCode());
            if (countMap == null) {
                countMap = new HashMap<>();
                map.put(e.getCookCode(), countMap);
            }
            Integer count = countMap.get(cookOrderType.getTypeDesc());
            if (count == null) {
                count = 0;
            }
            countMap.put(cookOrderType.getTypeDesc(), count + 1);
            map.put(e.getCookCode(), countMap);
        });
        return map;
    }
}
