package com.luwh.we.app.server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luwh.we.app.core.web.ResponsePageResult;

import java.util.ArrayList;

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

    @GetMapping("/kind/page")
    public ResponsePageResult selectMenuByPage(@RequestParam("page") Integer page,
            @RequestParam("page") Integer pageSize, @RequestParam("page") Integer search) {

        return ResponsePageResult.success(0, 10, 20, new ArrayList<>());
    }

}
