package com.luwh.we.app.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwh.we.app.core.web.ResponsePageResult;
import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.model.po.food.FoodKindPO;
import com.luwh.we.app.service.FoodKindService;

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
public class FoodMenuController {
    @Resource
    private FoodKindService foodKindService;

    @GetMapping("/type")
    public ResponseResult selectMenuByPage(@RequestParam(value = "search", required = false) String search) {
        List<String> strings = foodKindService.selectAllFoodKind(search);
        return ResponseResult.success(strings);
    }

    @GetMapping("/kind/page")
    public ResponsePageResult selectMenuByPage(@RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "search", required = false) String search) {
        Page<FoodKindPO> pageRes = foodKindService.selectFoodPage(page, pageSize, search);
        return ResponsePageResult.success(pageRes.getCurrent(), pageRes.getSize(), pageRes.getTotal(), pageRes.getRecords());
    }

}
