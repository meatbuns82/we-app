package com.luwh.we.app.service.passport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.dto.request.ThirdAccountRequest;
import com.luwh.we.app.dto.response.WechatAccessTokenResponse;
import com.luwh.we.app.model.po.passport.PassportUserPO;
import com.luwh.we.app.service.BaseService;

/**
 * @author lu.wh
 * @date 2023/10/19 17/25/51
 * @description
 */
public interface PassportService extends IService<PassportUserPO>, BaseService<PassportUserPO> {

    public PassportUserPO selectByOpenId(String openId);
    public boolean checkExistOpenId(String openid);
    public PassportUserPO loginByOpenId(String openId);
    public PassportUserPO registerAbsent(ThirdAccountRequest request, WechatAccessTokenResponse response);
}
