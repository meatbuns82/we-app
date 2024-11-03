package com.luwh.we.app.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.model.po.food.FoodImgContentPO;
import com.luwh.we.app.service.BaseService;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/12/05 15/40/07
 * @description
 */
public interface FoodImgContentService extends IService<FoodImgContentPO>, BaseService<FoodImgContentPO> {
    List<FoodImgContentPO> selectFoodImgContentByFoodCodes(List<String> foodCodes);
}
