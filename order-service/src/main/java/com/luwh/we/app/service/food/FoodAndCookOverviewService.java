package com.luwh.we.app.service.food;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwh.we.app.dto.response.FoodDetailOverviewResponse;
import com.luwh.we.app.dto.response.FoodKindResponse;

import java.util.List;

/**
 * 食物种类，和 具体的食物联合业务处理接口
 *
 * @author lu.wh
 * @date 2023/11/29 14/48/37
 * @description
 */
public interface FoodAndCookOverviewService {

    Page<FoodDetailOverviewResponse> selectFoodDetailOverview(Integer page, Integer pageSize, String foodCode, String search);

    Page<FoodDetailOverviewResponse> selectFoodDetailOverviewByCookCode(Integer page, Integer pageSize, List<String> cookCode);
    public List<FoodDetailOverviewResponse> selectFoodDetailList(List<String> cookCodes);

    Page<FoodKindResponse> selectFoodPage(Integer page, Integer pageSize, String search);
}
