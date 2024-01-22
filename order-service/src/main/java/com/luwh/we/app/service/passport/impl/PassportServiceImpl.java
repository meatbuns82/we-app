package com.luwh.we.app.service.passport.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.constants.Constants;
import com.luwh.we.app.dao.passport.PassportDao;
import com.luwh.we.app.dto.request.ThirdAccountRequest;
import com.luwh.we.app.dto.response.WechatAccessTokenResponse;
import com.luwh.we.app.model.po.passport.PassportUserPO;
import com.luwh.we.app.service.passport.PassportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lu.wh
 * @date 2023/10/19 17/27/55
 * @description
 */
@Service
public class PassportServiceImpl extends ServiceImpl<PassportDao, PassportUserPO>
        implements PassportService {
    private Logger logger = LoggerFactory.getLogger(PassportServiceImpl.class);

    public PassportUserPO loginByOpenId(String openId){
        PassportUserPO userPO = selectByOpenId(openId);
        userPO.setLastLoginTime(new Date());
        baseMapper.updateById(userPO);
        return userPO;
    }

    public boolean checkExistOpenId(String openid) {
        return selectByOpenId(openid) != null;
    }

    public PassportUserPO selectByOpenId(String openId){
        LambdaQueryWrapper<PassportUserPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PassportUserPO::getOpenId, openId)
                .last(Constants.SQL_LIMIT_ONE);
        return baseMapper.selectOne(wrapper);
    }

    public PassportUserPO registerAbsent(ThirdAccountRequest request, WechatAccessTokenResponse response) {
        PassportUserPO userPO = new PassportUserPO();
        userPO.setAccount(response.getOpenid());
        userPO.setNickName(request.getNickName());
        userPO.setAvatar(request.getAvatar());
        userPO.setOpenId(response.getOpenid());
        userPO.setPhone(request.getPhone());
        userPO.setSource(request.getSource());
        baseMapper.insert(userPO);
        return userPO;
    }

}
