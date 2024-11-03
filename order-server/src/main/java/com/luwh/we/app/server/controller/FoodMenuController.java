package com.luwh.we.app.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwh.we.app.core.annoa.ApiDesc;
import com.luwh.we.app.core.annoa.ApiModelDesc;
import com.luwh.we.app.core.web.ResponsePageResult;
import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.dto.response.FoodKindResponse;
import com.luwh.we.app.service.food.FoodAndCookOverviewService;
import com.luwh.we.app.service.food.FoodKindService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 食物的菜单管理
 *
 * @author lu.wh
 * @date 2023/09/25 14/23/19
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/food")
@ApiDesc(desc = "食物的种类")
public class FoodMenuController {
    @Resource
    private FoodKindService foodKindService;
    @Resource
    private FoodAndCookOverviewService foodAndCookOverviewService;
    @ApiModelDesc(desc = "查询食物的种类")
    @GetMapping("/type")
    public ResponseResult selectMenuByPage(@RequestParam(value = "search", required = false) String search) {
        List<String> strings = foodKindService.selectAllFoodKind(search);
        return ResponseResult.success(strings);
    }
    @ApiModelDesc(desc = "查询所有的食材")
    @GetMapping("/kind/page")
    public ResponsePageResult selectMenuByPage(@RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "search", required = false) String search) {
        Page<FoodKindResponse> pageRes = foodAndCookOverviewService.selectFoodPage(page, pageSize, search);

        return ResponsePageResult.success(pageRes.getCurrent(), pageRes.getSize(), pageRes.getTotal(), pageRes.getRecords());
    }

}
