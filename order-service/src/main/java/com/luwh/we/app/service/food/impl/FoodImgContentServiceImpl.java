package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.dao.food.FoodImgContentDao;
import com.luwh.we.app.model.po.food.FoodImgContent;
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
public class FoodImgContentServiceImpl extends ServiceImpl<FoodImgContentDao, FoodImgContent> implements FoodImgContentService {


    @Override
    public List<FoodImgContent> selectFoodImgContentByFoodCodes(List<String> foodCodes) {
        LambdaQueryWrapper<FoodImgContent> wrapper = queryWrapper();
        if(CollectionUtils.isEmpty(foodCodes)){
            return Collections.emptyList();
        }
        wrapper.in(FoodImgContent::getFoodCode, foodCodes);
        List<FoodImgContent> foodImgContents = baseMapper.selectList(wrapper);
        return foodImgContents;
    }
}
