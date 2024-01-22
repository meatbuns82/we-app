package com.luwh.we.app.server.controller.passport;

import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.dto.request.ThirdAccountRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private PassportUnionService passportUnionService;

    /**
     * 存在就登录，不存在就注销
     * @param request
     * @return
     */
    @PostMapping("/third/login")
    public ResponseResult thirdLogin(@RequestBody ThirdAccountRequest request){

        return  passportUnionService.thirdLogin(request);
    }
}
