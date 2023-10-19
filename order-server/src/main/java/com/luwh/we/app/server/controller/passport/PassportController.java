package com.luwh.we.app.server.controller.passport;

import com.luwh.we.app.core.web.ResponseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lu.wh
 * @date 2023/10/18 10/16/50
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/passport")
public class PassportController {

    @PostMapping("/third/login")
    public ResponseResult thirdLogin(){

        return ResponseResult.success("");
    }
}
