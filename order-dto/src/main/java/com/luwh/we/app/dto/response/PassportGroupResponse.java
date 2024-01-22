package com.luwh.we.app.dto.response;

import java.util.Date;

/**
 * @author lu.wh
 * @date 2023/12/01 15/09/54
 * @description
 */
public class PassportGroupResponse {
    private Long id;
    private String groupCode;
    private String groupName;
    private String groupHost; // 组当前的主人
    private String groupCreator;  // 组的创建人
    private Integer type;      // 组的类型是 永久组 1 还是临时组 0
    private Integer expireDays;  // 过期时间
    private Boolean allowGroupMemberInvite;  // 是否允许组员邀请别人
    private boolean deleted;
    private boolean defaultGroup;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupHost() {
        return groupHost;
    }

    public void setGroupHost(String groupHost) {
        this.groupHost = groupHost;
    }

    public String getGroupCreator() {
        return groupCreator;
    }

    public void setGroupCreator(String groupCreator) {
        this.groupCreator = groupCreator;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(Integer expireDays) {
        this.expireDays = expireDays;
    }

    public Boolean getAllowGroupMemberInvite() {
        return allowGroupMemberInvite;
    }

    public void setAllowGroupMemberInvite(Boolean allowGroupMemberInvite) {
        this.allowGroupMemberInvite = allowGroupMemberInvite;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
