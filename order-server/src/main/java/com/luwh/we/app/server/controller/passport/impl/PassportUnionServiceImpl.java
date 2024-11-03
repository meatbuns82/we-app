package com.luwh.we.app.server.controller.passport.impl;

import com.alibaba.fastjson.JSONObject;
import com.luwh.we.app.common.constants.Constants;
import com.luwh.we.app.common.exception.exceptions.OrderException;
import com.luwh.we.app.core.http.HttpRequestContent;
import com.luwh.we.app.core.http.RequestContext;
import com.luwh.we.app.core.properties.WechatProperties;
import com.luwh.we.app.core.util.AESUtil;
import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.core.web.enums.ResponseEnums;
import com.luwh.we.app.dto.request.ThirdAccountRequest;
import com.luwh.we.app.dto.response.PassportGroupResponse;
import com.luwh.we.app.dto.response.PassportUserResponse;
import com.luwh.we.app.dto.response.WechatAccessTokenResponse;
import com.luwh.we.app.model.po.passport.PassportGroupPO;
import com.luwh.we.app.model.po.passport.PassportUserPO;
import com.luwh.we.app.model.po.passport.UserGroupRelationInfoPO;
import com.luwh.we.app.server.controller.passport.PassportUnionService;
import com.luwh.we.app.service.passport.PassportGroupService;
import com.luwh.we.app.service.passport.PassportService;
import com.luwh.we.app.service.passport.UserGroupRelationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author lu.wh
 * @date 2023/12/01 14/59/12
 * @description
 */
@Service
public class PassportUnionServiceImpl implements PassportUnionService {
    private Logger logger = LoggerFactory.getLogger(PassportUnionServiceImpl.class);
    @Resource
    private PassportGroupService groupService;
    @Resource
    private PassportService passportService;
    @Resource
    private WechatProperties wechatProperties;
    @Resource
    private UserGroupRelationInfoService groupRelationInfoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult thirdLogin(ThirdAccountRequest request) {
        String loginCode = request.getCode();
        WechatAccessTokenResponse response = requestWechatUserId(loginCode);

        if (StringUtils.hasText(request.getEncryptedData()) && StringUtils.hasText(request.getIv())) {
            // 获取第三方账号关联的手机号, 这里要根据获取回来的解密的秘钥，然后根据加密向量和数据解密出来手机号，手机号密文内容是前端获取
            String phone = requirePhone(request.getSessionKey(), request.getEncryptedData(), request.getIv());
            logger.info("EncryptedData....{}, iv:{}..., resultPhone:{}", request.getEncryptedData(), request.getIv(),
                    phone);
//            request.setPhone(phone);
            // 获取真的手机号要钱，所以现在不用真的手机号，直接给个假的默认的
            request.setPhone("1300000000");
        }
        // 登录成功后 会返回一个OpenId, 这个openId 在一次授权期内是一直有效的，通过这个OpenId可以做授权登录
        boolean exist = passportService.checkExistOpenId(response.getOpenid());
//        boolean withPhone = true;
        boolean withPhone = StringUtils.hasText(request.getPhone());
        // 如果不存在
        if (!exist && !withPhone) {
            return ResponseResult.error(ResponseEnums.ABSENT_PARAMS_ERROR, "need phone info", response.getSession_key());
        } else if (!exist && withPhone) {
            passportService.registerAbsent(request, response);
            // 默认创建一个 分组
            createDefaultGroup(response.getOpenid());
        } else {
            ;
        }
        PassportUserPO userPO = passportService.loginByOpenId(response.getOpenid());
        PassportUserResponse passportUserResponse = userPO.toResp();
        fillGroupInfo(passportUserResponse);
        return ResponseResult.success(passportUserResponse);
    }

    /**
     * 填充用户的组信息
     *
     * @param passportUserResponse
     */
    private void fillGroupInfo(PassportUserResponse passportUserResponse) {
        String account = passportUserResponse.getAccount();
        List<UserGroupRelationInfoPO> groupRelationInfoPOS = groupRelationInfoService.selectAllGroupByAccount(account);
        AtomicReference<String> defaultGroupCode = new AtomicReference<>();
        List<String> groupCodes = groupRelationInfoPOS.stream().map(e ->{
            if(e.getDefaultGroup()){
                defaultGroupCode.set(e.getGroupCode());
            }
            return e.getGroupCode();
        }).collect(Collectors.toList());
        List<PassportGroupPO> passportGroupPOS = groupService.selectByCodes(groupCodes);
        List<PassportGroupResponse> collect = passportGroupPOS.stream().map(e -> {
            PassportGroupResponse groupResponse = e.toResp();
            if(groupResponse.getGroupCode().equals(defaultGroupCode.get())){
                groupResponse.setDefaultGroup(true);
            }
            return groupResponse;
        }).collect(Collectors.toList());
        passportUserResponse.setGroupResponse(collect);
    }

    /**
     * 创建默认组
     *
     * @param account
     */
    private void createDefaultGroup(String account) {
        PassportGroupPO passportGroupPO = new PassportGroupPO();
        passportGroupPO.setGroupCreator(account);
        passportGroupPO.setGroupHost(account);
        passportGroupPO.setDeleted(false);
        passportGroupPO.setType(1);
        passportGroupPO.setGroupName(Constants.DEFAULT_GROUP_NAME);
        groupService.addGroup(passportGroupPO);
        // 填充关系
        UserGroupRelationInfoPO userGroupRelationInfoPO = new UserGroupRelationInfoPO();
        userGroupRelationInfoPO.setDefaultGroup(true);
        userGroupRelationInfoPO.setGroupCode(passportGroupPO.getGroupCode());
        userGroupRelationInfoPO.setAccount(account);
        groupRelationInfoService.assignToGroup(userGroupRelationInfoPO);
    }

    /**
     * 请求查询电话
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    private String requirePhone(String sessionKey, String encryptedData, String iv) {
        String s = null;
        try {
            s = new AESUtil().build(sessionKey).decryptData(encryptedData, iv);
            return JSONObject.parseObject(s).getString("phoneNumber");
        } catch (Exception e) {
            throw new OrderException(e.toString());
        }
    }

    private WechatAccessTokenResponse requestWechatUserId(String loginCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("appid", wechatProperties.getAppid());
        params.put("secret", wechatProperties.getSecret());
        params.put("js_code", loginCode);
        params.put("grant_type", wechatProperties.getUnionIdGrantType());
        HttpRequestContent content =
                HttpRequestContent.build(wechatProperties.getOpenIdAndUnionIdAddress(), HttpMethod.GET, params);
        RequestContext.getInstance().request(content);
        String resultBody = content.getResultBody();
        WechatAccessTokenResponse wechatAccessTokenResponse = JSONObject.parseObject(resultBody, WechatAccessTokenResponse.class);
        return wechatAccessTokenResponse;
    }
}
