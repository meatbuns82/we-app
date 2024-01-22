package com.luwh.we.app.server.controller.vote;

import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.service.vote.VoteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lu.wh
 * @date 2023/11/08 14/07/36
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Resource
    private VoteService voteService;

    @PostMapping("vote")
    public ResponseResult vote(){

        return ResponseResult.success("");
    }
}
