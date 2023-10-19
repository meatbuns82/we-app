package com.luwh.we.app.server.controller;

import com.luwh.we.app.core.annoa.ApiDesc;
import com.luwh.we.app.core.annoa.ApiModelDesc;
import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.dto.response.CookDetailResponse;
import com.luwh.we.app.service.food.CookDetailService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @Resource
    private CookDetailService cookDetailService;
    @ApiModelDesc(desc = "查询食物的细节，分页")
    @GetMapping("/detail")
    public ResponseResult selectFoodDetail(@RequestParam("cookCode") String cookCode) {
        CookDetailResponse detailResponse = cookDetailService.selectCookCode(cookCode);
        return ResponseResult.success(detailResponse);
    }
}
