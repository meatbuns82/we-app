package com.luwh.we.app.server.controller;

import com.luwh.we.app.core.annoa.ApiModelDesc;
import com.luwh.we.app.core.context.UserContext;
import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.dto.request.OrderFoodRequest;
import com.luwh.we.app.dto.response.CookOrderResponse;
import com.luwh.we.app.server.service.UserFoodService;
import com.luwh.we.app.service.order.CookOrderService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 点菜
 *
 * @author lu.wh
 * @date 2023/11/29 17/52/40
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderFoodController {
    @Resource
    private UserFoodService userFoodService;
    @Resource
    private CookOrderService cookOrderService;

    @ApiModelDesc("查询已经点的食物车")
    @GetMapping("/select")
    public ResponseResult orderFoodCar(@RequestParam("account") String account,
                                       @RequestParam(value = "groupCode", required = false) String groupCode){
        if(!StringUtils.hasText(account)) {
            account = UserContext.getInstance().getUser();
        }
        List<CookOrderResponse> cookOrderResponses = userFoodService.orderFoodCar(account, groupCode);
        return ResponseResult.success(cookOrderResponses);
    }

    @ApiModelDesc(desc = "查询今天要吃的东西")
    @GetMapping("/select/current-day")
    public ResponseResult selectOrderFoodCarToday(@RequestParam("account") String account,
                                                  @RequestParam(value = "groupCode", required = false) String groupCode) {
        if(!StringUtils.hasText(account)) {
            account = UserContext.getInstance().getUser();
        }

        List<CookOrderResponse> cookOrderResponses = userFoodService.selectOrderFoodCarToday(account, groupCode);
        return ResponseResult.success(cookOrderResponses);
    }

    @ApiModelDesc("点菜")
    @PostMapping("/increaseOrder")
    public ResponseResult orderFood(@RequestBody OrderFoodRequest request){
        if(!StringUtils.hasText(request.getAccount())) {
            request.setAccount(UserContext.getInstance().getUser());
        }
        cookOrderService.orderFood(request);
        return ResponseResult.success("");
    }

    @ApiModelDesc("删除点菜")
    @DeleteMapping("/deOrder")
    public ResponseResult deOrderFood(@RequestParam("account") String account, @RequestParam("cookCode") String cookCode,
                                      @RequestParam(value = "groupCode", required = false) String groupCode){
        if(!StringUtils.hasText(account)) {
            account = UserContext.getInstance().getUser();
        }
        cookOrderService.deOrderFood(account, cookCode, groupCode);
        return ResponseResult.success("");
    }

    @ApiModelDesc("点餐的总数")
    @GetMapping("/sum")
    public ResponseResult sumOrderFood(@RequestParam("account") String account,
                                      @RequestParam(value = "groupCode", required = false) String groupCode){
        if(!StringUtils.hasText(account)) {
            account = UserContext.getInstance().getUser();
        }
        Integer integer = cookOrderService.sumOrderFood(account, groupCode);
        return ResponseResult.success(integer);
    }

}
