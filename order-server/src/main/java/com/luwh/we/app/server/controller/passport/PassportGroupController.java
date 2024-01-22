package com.luwh.we.app.server.controller.passport;

import com.luwh.we.app.core.web.ResponseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lu.wh
 * @date 2023/11/30 17/41/23
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/passport/group")
public class PassportGroupController {

    public ResponseResult selectGroupByAccount(@RequestParam("account") String account){

        return ResponseResult.success("");
    }

    public ResponseResult addGroup(){

        return ResponseResult.success("");
    }

    public ResponseResult deleteGroup(){

        return ResponseResult.success("");
    }

    public ResponseResult visitGroup(){

        return ResponseResult.success("");
    }
}
