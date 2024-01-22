package com.luwh.we.app.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.dto.request.OrderFoodRequest;
import com.luwh.we.app.model.po.order.CookOrderPO;
import com.luwh.we.app.service.BaseService;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/11/30 11/22/21
 * @description
 */
public interface CookOrderService extends IService<CookOrderPO>, BaseService<CookOrderPO> {
    void orderFood(OrderFoodRequest request);

    List<CookOrderPO> selectOrderFood(String account, String groupCode);

    void deOrderFood(String account, String cookCode, String groupCode);

    /**
     * 获取符合条件的个数
     * @param account
     * @param cookCode
     * @param groupCode
     */
    Integer sumOrderFood(String account, String groupCode);
}
