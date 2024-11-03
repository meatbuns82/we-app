package com.luwh.we.app.service.food;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.dto.response.FoodDetailOverviewResponse;
import com.luwh.we.app.model.po.food.FoodDetailOverviewPO;
import com.luwh.we.app.service.BaseService;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/09/27 10/40/21
 * @description
 */
public interface FoodDetailOverviewService extends IService<FoodDetailOverviewPO>, BaseService<FoodDetailOverviewPO> {
    Page<FoodDetailOverviewPO> selectFoodDetailOverviewPage(Integer page, Integer pageSize, String foodCode, String search);

    Page<FoodDetailOverviewPO> selectFoodDetailOverviewPageByCookCode(Integer page, Integer pageSize, List<String> cookCode);

    /**
     * 切换这个食物是不是展示
     * @param cookCode
     */
    void foodSwitch(String cookCode);
    FoodDetailOverviewPO selectByCookCode(String cookCode);
    List<FoodDetailOverviewPO> selectFoodDetailOverview(List<String> cookCodes);
}
