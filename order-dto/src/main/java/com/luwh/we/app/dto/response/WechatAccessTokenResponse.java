package com.luwh.we.app.dto.response;

/**
 * @author lu.wh
 * @date 2023/09/08 16/18/14
 * @description
 */
public class WechatAccessTokenResponse {

    private Long errcode;
    private String openid;   // OPENID", 授权用户唯一标识
    private String session_key;
    private String unionid; // "UNIONID" 用户统一标识。针对一个微信开放平台账号下的应用，同一用户的 unionid 是唯一的

    public Long getErrcode() {
        return errcode;
    }

    public void setErrcode(Long errcode) {
        this.errcode = errcode;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "WechatAccessTokenResponse{" +
                "errcode=" + errcode +
                ", openid='" + openid + '\'' +
                ", session_key='" + session_key + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
