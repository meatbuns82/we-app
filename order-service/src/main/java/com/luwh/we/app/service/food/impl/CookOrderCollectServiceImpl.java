package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.enums.CookOrderTypeEnums;
import com.luwh.we.app.dao.food.UserCookCollectRelationInfoDao;
import com.luwh.we.app.model.po.food.UserCookCollectRelationInfoPO;
import com.luwh.we.app.service.food.CookOrderCollectService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lu.wh
 * @date 2023/12/04 14/50/21
 * @description
 */
@Service
public class CookOrderCollectServiceImpl extends ServiceImpl<UserCookCollectRelationInfoDao, UserCookCollectRelationInfoPO> implements CookOrderCollectService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void goodCook(String cookCode, String account) {
        // 一个用户只能点赞一次， 如果点赞会把点踩取消
        if (checkHasGood(cookCode, account)) {
            return;
        }
        cancel(cookCode, account, 1);
        UserCookCollectRelationInfoPO po = new UserCookCollectRelationInfoPO(account, cookCode, 0);
        baseMapper.insert(po);
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
        baseMapper.insert(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void collectCook(String cookCode, String account) {
        // 一个用户只能收藏一次，同时会点赞
        if (checkHasCollect(cookCode, account)) {
            return;
        }
        // 点赞，收藏
        ((CookOrderCollectService) AopContext.currentProxy()).goodCook(cookCode, account);
        UserCookCollectRelationInfoPO po = new UserCookCollectRelationInfoPO(account, cookCode, 2);
        baseMapper.insert(po);
    }

    @Override
    public  Map<String, Map<String, Integer>> countTypeByCookCodes(List<String> cookCodes) {
        if(CollectionUtils.isEmpty(cookCodes)){
            return new HashMap<>();
        }
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.in(UserCookCollectRelationInfoPO::getCookCode ,cookCodes);
        List<UserCookCollectRelationInfoPO> infoPOS = baseMapper.selectList(wrapper);
        // 按照cookCode分好
        Map<String, Map<String, Integer>> map = new HashMap<>();
        infoPOS.stream().forEach(e -> {
            // 每个cookCode里按照 不同的type分类好
            CookOrderTypeEnums cookOrderType = CookOrderTypeEnums.fromVal(e.getType());
            Map<String, Integer> countMap = map.get(e.getCookCode());
            if(countMap == null){
                countMap = new HashMap<>();
                map.put(e.getCookCode(), countMap);
            }
            Integer count = countMap.get(cookOrderType.getTypeDesc());
            if(count == null){
                count = 0;
            }
            countMap.put(cookOrderType.getTypeDesc(), count + 1);
            map.put(e.getCookCode(), countMap);
        });
        return map;
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
        baseMapper.delete(wrapper);
    }

    private boolean checkHasGood(String cookCode, String account) {
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getCookCode, cookCode)
                .eq(UserCookCollectRelationInfoPO::getAccount, account)
                .eq(UserCookCollectRelationInfoPO::getType, 0);
        UserCookCollectRelationInfoPO userCookCollectRelationInfoPO = baseMapper.selectOne(wrapper);
        return userCookCollectRelationInfoPO != null;
    }

    private boolean checkHasBad(String cookCode, String account) {
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getCookCode, cookCode)
                .eq(UserCookCollectRelationInfoPO::getAccount, account)
                .eq(UserCookCollectRelationInfoPO::getType, 1);
        UserCookCollectRelationInfoPO userCookCollectRelationInfoPO = baseMapper.selectOne(wrapper);
        return userCookCollectRelationInfoPO != null;
    }

    private boolean checkHasCollect(String cookCode, String account) {
        LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserCookCollectRelationInfoPO::getCookCode, cookCode)
                .eq(UserCookCollectRelationInfoPO::getAccount, account)
                .eq(UserCookCollectRelationInfoPO::getType, 2);
        UserCookCollectRelationInfoPO userCookCollectRelationInfoPO = baseMapper.selectOne(wrapper);
        return userCookCollectRelationInfoPO != null;
    }
}
