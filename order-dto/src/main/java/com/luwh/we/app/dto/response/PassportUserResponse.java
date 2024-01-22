package com.luwh.we.app.dto.response;

import java.util.Date;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/12/01 15/09/15
 * @description
 */
public class PassportUserResponse {
    private Long id;
    private String account;
    private String avatar;
    private String nickName; // 第三方 账号的昵称
    private String phone;
    private String openId;
    private String source; // 账号的来源
    private Date createTime;
    private Date updateTime;
    private Date lastLoginTime;

    private List<PassportGroupResponse> groupResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<PassportGroupResponse> getGroupResponse() {
        return groupResponse;
    }

    public void setGroupResponse(List<PassportGroupResponse> groupResponse) {
        this.groupResponse = groupResponse;
    }
}
