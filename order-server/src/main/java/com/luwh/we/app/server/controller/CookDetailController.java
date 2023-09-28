package com.luwh.we.app.server.controller;

import com.luwh.we.app.core.annoa.ApiDesc;
import com.luwh.we.app.core.annoa.ApiModelDesc;
import com.luwh.we.app.core.web.ResponseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu.wh
 * @date 2023/09/27 16/24/06
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/cook")
@ApiDesc(desc = "食物的细节，包括做法，图片，用料等详细的信息")
public class CookDetailController {

    @ApiModelDesc(desc = "查询食物的细节，分页")
    @GetMapping("/detail")
    public ResponseResult selectFoodDetailOverview(@RequestParam("page") Integer page,
                                                   @RequestParam("pageSize") Integer pageSize,
                                                   @RequestParam("cookCode") String cookCode) {
//        Page<FoodDetailOverviewPO> pageRes = detailOverviewService.selectFoodDetailOverviewPage(page, pageSize, foodCode, search);
        return ResponseResult.success("");
    }
}
