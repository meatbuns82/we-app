package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.dao.food.FoodImgContentDao;
import com.luwh.we.app.model.po.food.FoodImgContentPO;
import com.luwh.we.app.service.food.FoodImgContentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/12/05 15/41/02
 * @description
 */
@Service
public class FoodImgContentServiceImpl extends ServiceImpl<FoodImgContentDao, FoodImgContentPO> implements FoodImgContentService {


    @Override
    public List<FoodImgContentPO> selectFoodImgContentByFoodCodes(List<String> foodCodes) {
        LambdaQueryWrapper<FoodImgContentPO> wrapper = queryWrapper();
        if(CollectionUtils.isEmpty(foodCodes)){
            return Collections.emptyList();
        }
        wrapper.in(FoodImgContentPO::getFoodCode, foodCodes);
        List<FoodImgContentPO> foodImgContents = baseMapper.selectList(wrapper);
        return foodImgContents;
    }
}
