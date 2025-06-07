package com.luwh.we.app.server.service;

import com.luwh.we.app.dto.response.CookOrderResponse;

import java.util.List;

/**
 * 用户-群组-食物 联合业务处理接口
 *
 * @author lu.wh
 * @date 2023/11/30 11/18/35
 * @description
 */
public interface UserFoodService {

    /**
     * 查看已经点过的菜列表
     * @param account
     * @param cookCode
     * @param groupCode
     */
    List<CookOrderResponse> orderFoodCar(String account, String groupCode);

    /**
     * 仅查询当天的点过的菜列表
     * @param account
     * @param groupCode
     * @return
     */
    List<CookOrderResponse> selectOrderFoodCarToday(String account, String groupCode);
}
