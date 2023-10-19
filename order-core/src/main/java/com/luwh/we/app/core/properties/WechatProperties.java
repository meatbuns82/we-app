package com.luwh.we.app.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lu.wh
 * @date 2023/10/19 11/33/04
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {
    private String openIdAndUnionIdAddress = "https://api.weixin.qq.com/sns/jscode2session";
    private String tokenAddress = "https://api.weixin.qq.com/cgi-bin/token"; // ?grant_type=client_credential&appid=%s&secret=%s
    private String phoneAddress = "https://api.weixin.qq.com/wxa/business/getuserphonenumber"; // ?access_token=
    private String accessTokenMethod  = "GET";
    private String appid;
    private String secret;
    private String unionIdGrantType = "authorization_code";
    private String accessTokenGrantType = "client_credential";

    public String getOpenIdAndUnionIdAddress() {
        return openIdAndUnionIdAddress;
    }

    public void setOpenIdAndUnionIdAddress(String openIdAndUnionIdAddress) {
        this.openIdAndUnionIdAddress = openIdAndUnionIdAddress;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public String getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(String phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public String getAccessTokenMethod() {
        return accessTokenMethod;
    }

    public void setAccessTokenMethod(String accessTokenMethod) {
        this.accessTokenMethod = accessTokenMethod;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUnionIdGrantType() {
        return unionIdGrantType;
    }

    public void setUnionIdGrantType(String unionIdGrantType) {
        this.unionIdGrantType = unionIdGrantType;
    }

    public String getAccessTokenGrantType() {
        return accessTokenGrantType;
    }

    public void setAccessTokenGrantType(String accessTokenGrantType) {
        this.accessTokenGrantType = accessTokenGrantType;
    }
}
