package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.constants.SqlConstants;
import com.luwh.we.app.dao.food.FoodDetailOverviewDao;
import com.luwh.we.app.model.po.food.FoodDetailOverviewPO;
import com.luwh.we.app.service.food.FoodDetailOverviewService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
        if(StringUtils.hasText(foodCode)){
            queryWrapper.in(FoodDetailOverviewPO::getFoodCode, foodCode);
        }
        if (StringUtils.hasText(search)) {
            queryWrapper.like(FoodDetailOverviewPO::getCookDetailName, search).or()
                    .like(FoodDetailOverviewPO::getCookIngredient, search);
        }
        queryWrapper.orderByAsc(FoodDetailOverviewPO::getCookDetailName);
        Page<FoodDetailOverviewPO> foodDetailOverviewPOPage = baseMapper.selectPage(pageObj, queryWrapper);
        return foodDetailOverviewPOPage;
    }

    @Override
    public Page<FoodDetailOverviewPO> selectFoodDetailOverviewPageByCookCode(Integer page, Integer pageSize, List<String> cookCode) {
        Page<FoodDetailOverviewPO> pageObj = new Page<>(page, pageSize);
        LambdaQueryWrapper<FoodDetailOverviewPO> queryWrapper = queryWrapper();
        if(!CollectionUtils.isEmpty(cookCode)){
            queryWrapper.in(FoodDetailOverviewPO::getCookCode, cookCode);
        }
        queryWrapper.orderByAsc(FoodDetailOverviewPO::getCookDetailName);
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
                .last(SqlConstants.SQL_LIMIT_ONE);
        FoodDetailOverviewPO foodDetailOverviewPO = baseMapper.selectOne(queryWrapper);
        return foodDetailOverviewPO;
    }

    @Override
    public List<FoodDetailOverviewPO> selectFoodDetailOverview(List<String> cookCodes) {
        if(CollectionUtils.isEmpty(cookCodes)){
            return new ArrayList<>();
        }
        LambdaQueryWrapper<FoodDetailOverviewPO> queryWrapper = queryWrapper();
        queryWrapper.in(FoodDetailOverviewPO::getCookCode, cookCodes);
        List<FoodDetailOverviewPO> foodDetailOverviewPOS = baseMapper.selectList(queryWrapper);
        return foodDetailOverviewPOS;
    }
}
