package com.luwh.we.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.dao.FoodKindDao;
import com.luwh.we.app.model.po.food.FoodKindPO;
import com.luwh.we.app.service.BaseService;
import com.luwh.we.app.service.FoodKindService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lu.wh
 * @date 2023/09/25 15/20/34
 * @description
 */
@Service
public class FoodKindServiceImpl extends ServiceImpl<FoodKindDao, FoodKindPO> implements FoodKindService, BaseService<FoodKindPO> {

    @Override
    public List<String> selectAllFoodKind(String search) {
        LambdaQueryWrapper<FoodKindPO> queryWrapper = queryWrapper();
        queryWrapper.select(FoodKindPO::getFoodKind);
        if (StringUtils.hasText(search)) {
            queryWrapper.like(FoodKindPO::getFoodKind, search);
        }
        queryWrapper.groupBy(FoodKindPO::getFoodKind);
        List<FoodKindPO> foodKindPOS = baseMapper.selectList(queryWrapper);
        List<String> kinds = foodKindPOS.stream().map(e -> e.getFoodKind()).collect(Collectors.toList());
        Collections.sort(kinds, (o1, o2) -> o1.compareTo(o2));
        return kinds;
    }

    @Override
    public Page<FoodKindPO> selectFoodPage(Integer page, Integer pageSize, String search) {
        LambdaQueryWrapper<FoodKindPO> queryWrapper = queryWrapper();
        Page<FoodKindPO> pageObj = new Page<>(page, pageSize);
        if (StringUtils.hasText(search)) {
            queryWrapper.like(FoodKindPO::getFoodKind, search);
        }
        Page<FoodKindPO> foodKindPOPage = baseMapper.selectPage(pageObj, queryWrapper);
        return foodKindPOPage;
    }
}
