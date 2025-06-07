package com.luwh.we.app.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.exception.exceptions.OrderException;
import com.luwh.we.app.dao.order.CookOrderDao;
import com.luwh.we.app.dto.request.OrderFoodRequest;
import com.luwh.we.app.model.po.order.CookOrderPO;
import com.luwh.we.app.model.po.passport.PassportGroupPO;
import com.luwh.we.app.service.order.CookOrderService;
import com.luwh.we.app.service.passport.PassportGroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/11/30 11/37/29
 * @description
 */
@Service
public class CookOrderServiceImpl extends ServiceImpl<CookOrderDao, CookOrderPO> implements CookOrderService {

    @Resource
    private PassportGroupService groupService;

    @Override
    public void orderFood(OrderFoodRequest request) {
        CookOrderPO cookOrderPO = new CookOrderPO();
        cookOrderPO.setCookCode(request.getCookCode());
        cookOrderPO.setCreateUser(request.getAccount());
        cookOrderPO.setType(request.getType());
        cookOrderPO.setGroupCode(request.getGroupCode());
        fillPlanTime(cookOrderPO);
        checkExist(cookOrderPO);
        baseMapper.insert(cookOrderPO);
    }

    private void fillPlanTime(CookOrderPO cookOrderPO) {
        // planTime -> defulat CurrentTime, otherwise use group planTime
        if(cookOrderPO.getPlanTime() == null){
            PassportGroupPO passportGroupPOS = groupService.selectByCode(cookOrderPO.getGroupCode());
            // TODO  if group is an permanent group, may be haven`t plan time
            if(passportGroupPOS.isDefaultGroup()){
                // If default group ,set today
                cookOrderPO.setPlanTime(new Date());
            }else {
                cookOrderPO.setPlanTime(passportGroupPOS.getPlanTime());

            }
        }
    }

    private void checkExist(CookOrderPO cookOrderPO) {
        Date planTime = cookOrderPO.getPlanTime();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(planTime.toInstant(), ZoneId.systemDefault());
        LocalDateTime start = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCookCode, cookOrderPO.getCookCode())
                .eq(CookOrderPO::getGroupCode, cookOrderPO.getGroupCode())
                .eq(CookOrderPO::getCreateUser, cookOrderPO.getCreateUser())
                // 计划日当天的所有食物
                .between(CookOrderPO::getPlanTime, start, end);
        CookOrderPO res = baseMapper.selectOne(wrapper);
        if (res != null) {
            throw new OrderException("已经点过这道菜了嗷<(＾－＾)>");
        }
    }

    @Override
    public List<CookOrderPO> selectOrderFood(String account, String groupCode) {
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCreateUser, account)
                .eq(CookOrderPO::getGroupCode, groupCode)
                .orderByDesc(CookOrderPO::getCreateTime);
        // Only query future and current day, history not show, We think that those have already expired
        wrapper.gt(CookOrderPO::getPlanTime, LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        List<CookOrderPO> cookOrderPOS = baseMapper.selectList(wrapper);
        return cookOrderPOS;
    }

    @Override
    public List<CookOrderPO> selectOrderFoodCurrentDay(String account, String groupCode) {
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCreateUser, account)
                .eq(CookOrderPO::getGroupCode, groupCode)
                .orderByDesc(CookOrderPO::getCreateTime);
        // Only query today
        wrapper.gt(CookOrderPO::getPlanTime, LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        wrapper.lt(CookOrderPO::getPlanTime, LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        List<CookOrderPO> cookOrderPOS = baseMapper.selectList(wrapper);
        return cookOrderPOS;
    }

    @Override
    public void deOrderFood(String account, String cookCode, String groupCode) {
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCreateUser, account)
                .eq(CookOrderPO::getCookCode, cookCode);
        if (StringUtils.hasText(groupCode)) {
            wrapper.eq(CookOrderPO::getGroupCode, groupCode);
        }
        baseMapper.delete(wrapper);
    }

    @Override
    public Integer sumOrderFood(String account, String groupCode) {
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCreateUser, account);
        if (StringUtils.hasText(groupCode)) {
            wrapper.eq(CookOrderPO::getGroupCode, groupCode);
        }
        // TODO according time
        wrapper.gt(CookOrderPO::getPlanTime, LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        Integer integer = baseMapper.selectCount(wrapper);
        return integer;
    }
}
