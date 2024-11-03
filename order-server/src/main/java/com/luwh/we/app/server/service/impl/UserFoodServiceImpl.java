package com.luwh.we.app.server.service.impl;

import com.luwh.we.app.core.cache.PictureCacheManager;
import com.luwh.we.app.dto.response.CookOrderResponse;
import com.luwh.we.app.dto.response.FoodDetailOverviewResponse;
import com.luwh.we.app.dto.response.FoodKindResponse;
import com.luwh.we.app.model.po.food.FoodKindPO;
import com.luwh.we.app.model.po.order.CookOrderPO;
import com.luwh.we.app.server.service.UserFoodService;
import com.luwh.we.app.service.food.FoodAndCookOverviewService;
import com.luwh.we.app.service.food.FoodKindService;
import com.luwh.we.app.service.order.CookOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lu.wh
 * @date 2023/11/30 11/19/29
 * @description
 */
@Service
public class UserFoodServiceImpl implements UserFoodService {
    @Resource
    private CookOrderService cookOrderService;
    @Resource
    private FoodAndCookOverviewService foodAndCookOverviewService;
    @Resource
    private PictureCacheManager pictureCacheManager;
    @Resource
    private FoodKindService foodKindService;
    @Override
    public List<CookOrderResponse> orderFoodCar(String account, String groupCode) {
        List<CookOrderPO> cookOrderPOS = cookOrderService.selectOrderFood(account, groupCode);
        List<String> cookCodes = new ArrayList<>();
        Map<String, CookOrderPO> cookOrders = new HashMap<>();
        cookOrderPOS.stream().forEach(e -> {cookCodes.add(e.getCookCode()); cookOrders.put(e.getCookCode(), e);});

        List<FoodDetailOverviewResponse> responses = foodAndCookOverviewService.selectFoodDetailList(cookCodes);
        List<FoodKindPO> foodKindPOS = foodKindService.selectFoodKindByFoodCodes(cookCodes);
        List<CookOrderResponse> result = new ArrayList<>();
        responses.stream().forEach(e -> {
            CookOrderPO cookOrderPO = cookOrders.get(e.getCookCode());
            CookOrderResponse cookOrderResponse = cookOrderPO.toResp();

            e.setPicturePath(pictureCacheManager.getLocalCachePath(e.getCookCode()));

            cookOrderResponse.initFrom(e);

            result.add(cookOrderResponse);
        });
        //
        foodKindPOS.stream().forEach(e -> {
            CookOrderPO cookOrderPO = cookOrders.get(e.getFoodCode());
            CookOrderResponse cookOrderResponse = cookOrderPO.toResp();
            FoodKindResponse foodKindResponse = e.toResp();
            cookOrderResponse.setPicturePath(pictureCacheManager.getLocalCachePath(e.getFoodCode()));

            cookOrderResponse.initFrom(foodKindResponse);
            result.add(cookOrderResponse);
        });
        return result;
    }
}
