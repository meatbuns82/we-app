package com.luwh.we.app.service.food;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.model.po.food.FoodDetailOverviewPO;
import com.luwh.we.app.service.BaseService;

/**
 * @author lu.wh
 * @date 2023/09/27 10/40/21
 * @description
 */
public interface FoodDetailOverviewService extends IService<FoodDetailOverviewPO>, BaseService<FoodDetailOverviewPO> {
    Page<FoodDetailOverviewPO> selectFoodDetailOverviewPage(Integer page, Integer pageSize, String foodCode, String search);

    void foodSwitch(String cookCode);

    FoodDetailOverviewPO selectByCookCode(String cookCode);
}
