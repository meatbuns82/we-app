package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.constants.Constants;
import com.luwh.we.app.dao.FoodDetailOverviewDao;
import com.luwh.we.app.model.po.food.FoodDetailOverviewPO;
import com.luwh.we.app.service.food.FoodDetailOverviewService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author lu.wh
 * @date 2023/09/27 15/12/08
 * @description
 */
@Service
public class FoodDetailOverviewServiceImpl extends ServiceImpl<FoodDetailOverviewDao, FoodDetailOverviewPO>
        implements FoodDetailOverviewService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<FoodDetailOverviewPO> selectFoodDetailOverviewPage(Integer page, Integer pageSize, String foodCode, String search) {
        Page<FoodDetailOverviewPO> pageObj = new Page<>(page, pageSize);
        LambdaQueryWrapper<FoodDetailOverviewPO> queryWrapper = queryWrapper();
        queryWrapper.eq(FoodDetailOverviewPO::getFoodCode, foodCode);
        if (StringUtils.hasText(search)) {
            queryWrapper.like(FoodDetailOverviewPO::getCookDetailName, search);
        }
        Page<FoodDetailOverviewPO> foodDetailOverviewPOPage = baseMapper.selectPage(pageObj, queryWrapper);
        return foodDetailOverviewPOPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void foodSwitch(String cookCode) {
        FoodDetailOverviewService foodDetailOverviewService = (FoodDetailOverviewService) AopContext.currentProxy();
        FoodDetailOverviewPO foodDetailOverviewPO = foodDetailOverviewService.selectByCookCode(cookCode);
        LambdaUpdateWrapper<FoodDetailOverviewPO> updateWrapper = updateWrapper();
        updateWrapper.set(FoodDetailOverviewPO::getEnable, !foodDetailOverviewPO.getEnable())
                .eq(FoodDetailOverviewPO::getCookCode, cookCode);
        baseMapper.update(new FoodDetailOverviewPO(), updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public FoodDetailOverviewPO selectByCookCode(String cookCode) {
        LambdaQueryWrapper<FoodDetailOverviewPO> queryWrapper = queryWrapper();
        queryWrapper.eq(FoodDetailOverviewPO::getCookCode, cookCode)
                .last(Constants.SQL_LIMIT_ONE);
        FoodDetailOverviewPO foodDetailOverviewPO = baseMapper.selectOne(queryWrapper);
        return foodDetailOverviewPO;
    }
}
