package com.luwh.we.app.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwh.we.app.core.annoa.ApiModelDesc;
import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.dto.request.CookOrderCollectRequest;
import com.luwh.we.app.dto.response.CookCollectResponse;
import com.luwh.we.app.service.food.CookOrderCollectService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 对某个菜 的点赞，收藏，点踩的操作
 *
 * @author lu.wh
 * @date 2023/12/04 14/43/59
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/cook/collect")
public class CookOrderCollectController {

    @Resource
    private CookOrderCollectService cookOrderCollectService;

    @ApiModelDesc(desc = "给某个食物点赞")
    @PostMapping("/good")
    public ResponseResult good(@RequestBody CookOrderCollectRequest request){
        cookOrderCollectService.goodCook(request.getCookCode(), request.getAccount());
        return ResponseResult.success("success");
    }


    @ApiModelDesc(desc = "给某个食物点不喜欢")
    @PostMapping("/bad")
    public ResponseResult bad(@RequestBody CookOrderCollectRequest request){
        cookOrderCollectService.badCook(request.getCookCode(), request.getAccount());
        return ResponseResult.success("success");
    }


    @ApiModelDesc(desc = "收藏 cook")
    @PostMapping("/")
    public ResponseResult collect(@RequestBody CookOrderCollectRequest request){
        cookOrderCollectService.collectCook(request.getCookCode(), request.getAccount(), request.getCollect());
        return ResponseResult.success("success");
    }

    @ApiModelDesc(desc = "收藏 cook 的列表")
    @GetMapping("/")
    public ResponseResult<Page<CookCollectResponse>> collect(
            @RequestParam("account") String account,
            @RequestParam("page") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
            ){
        return ResponseResult.success(cookOrderCollectService.collectList(account, page, size));
    }
}
