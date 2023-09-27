package com.luwh.we.app.service.food;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwh.we.app.model.po.food.FoodKindPO;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/09/27 10/40/21
 * @description
 */
public interface FoodDetailOverviewService {
    List<String> selectFoodDetailOverviewPage(Integer page, Integer pageSize, String search);

    Page<FoodKindPO> foodSwitch(String cookCode);
}
