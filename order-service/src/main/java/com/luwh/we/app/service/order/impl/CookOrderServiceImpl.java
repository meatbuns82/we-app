package com.luwh.we.app.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.dao.order.CookOrderDao;
import com.luwh.we.app.dto.request.OrderFoodRequest;
import com.luwh.we.app.model.po.order.CookOrderPO;
import com.luwh.we.app.service.order.CookOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/11/30 11/37/29
 * @description
 */
@Service
public class CookOrderServiceImpl extends ServiceImpl<CookOrderDao, CookOrderPO> implements CookOrderService {

    @Override
    public void orderFood(OrderFoodRequest request) {
        CookOrderPO cookOrderPO = new CookOrderPO();
        cookOrderPO.setCookCode(request.getCookCode());
        cookOrderPO.setCreateUser(request.getAccount());
        cookOrderPO.setType(request.getType());
        cookOrderPO.setGroupCode(request.getGroupCode());
        checkExist(cookOrderPO);
        baseMapper.insert(cookOrderPO);
    }

    private void checkExist(CookOrderPO cookOrderPO) {
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCookCode, cookOrderPO.getCookCode())
                .eq(CookOrderPO::getGroupCode, cookOrderPO.getGroupCode())
                .eq(CookOrderPO::getCreateUser, cookOrderPO.getCreateUser());
        CookOrderPO res = baseMapper.selectOne(wrapper);
        if(res != null){
            throw new RuntimeException("已经点过这道菜了嗷<(＾－＾)>");
        }
    }

    @Override
    public List<CookOrderPO> selectOrderFood(String account, String groupCode) {
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCreateUser, account)
                .eq(CookOrderPO::getGroupCode, groupCode)
                .orderByDesc(CookOrderPO::getCreateTime);
        List<CookOrderPO> cookOrderPOS = baseMapper.selectList(wrapper);
        return cookOrderPOS;
    }

    @Override
    public void deOrderFood(String account, String cookCode, String groupCode) {
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCreateUser, account)
                .eq(CookOrderPO::getCookCode, cookCode);
        if(StringUtils.hasText(groupCode)) {
            wrapper.eq(CookOrderPO::getGroupCode, groupCode);
        }
        baseMapper.delete(wrapper);
    }

    @Override
    public Integer sumOrderFood(String account, String groupCode) {
        LambdaQueryWrapper<CookOrderPO> wrapper = queryWrapper();
        wrapper.eq(CookOrderPO::getCreateUser, account);
        if(StringUtils.hasText(groupCode)){
            wrapper.eq(CookOrderPO::getGroupCode, groupCode);
        }
        Integer integer = baseMapper.selectCount(wrapper);
        return integer;
    }
}
