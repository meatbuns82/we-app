package com.luwh.we.app.model.po.food;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author lu.wh
 * @date 2023/12/04 14/40/48
 * @description
 */
@TableName("user_cook_collect_relation_info")
public class UserCookCollectRelationInfoPO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String account;
    private String cookCode;
    private Integer type;  // 0 点赞 1踩  2 收藏
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    public UserCookCollectRelationInfoPO() {
    }

    public UserCookCollectRelationInfoPO(String account, String cookCode, Integer type) {
        this.account = account;
        this.cookCode = cookCode;
        this.type = type;
    }

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

    public String getCookCode() {
        return cookCode;
    }

    public void setCookCode(String cookCode) {
        this.cookCode = cookCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
}
