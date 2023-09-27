package com.luwh.we.app.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwh.we.app.core.annoa.ApiDesc;
import com.luwh.we.app.core.annoa.ApiModelDesc;
import com.luwh.we.app.core.web.ResponsePageResult;
import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.model.po.food.FoodKindPO;
import com.luwh.we.app.service.food.FoodDetailOverviewService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/09/27 10/35/24
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/food/detail")
@ApiDesc(desc = "食物细节的预览")
public class FoodDetailOverviewController {
    @Resource
    private FoodDetailOverviewService detailOverviewService;

    @ApiModelDesc(desc = "查询食物的种类")
    @GetMapping("/page")
    public ResponseResult selectFoodDetailOverview(@RequestParam("page") Integer page,
                                                   @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "search", required = false) String search) {
        List<String> strings = detailOverviewService.selectFoodDetailOverviewPage(page, pageSize, search);
        return ResponseResult.success(strings);
    }

    @ApiModelDesc(desc = "打开或关闭这个食物是否允许点单")
    @GetMapping("/status/change")
    public ResponsePageResult foodSwitch(@RequestParam("cookCode") String cookCode) {
        Page<FoodKindPO> pageRes = detailOverviewService.foodSwitch(cookCode);
        return ResponsePageResult.success(pageRes.getCurrent(), pageRes.getSize(), pageRes.getTotal(), pageRes.getRecords());
    }
}
