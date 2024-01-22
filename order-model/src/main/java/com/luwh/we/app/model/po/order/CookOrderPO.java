package com.luwh.we.app.model.po.order;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.luwh.we.app.dto.response.CookOrderResponse;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author lu.wh
 * @date 2023/11/30 11/23/00
 * @description
 */
@TableName("cook_order")
public class CookOrderPO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String cookCode;
    private String createUser;
    private String groupCode;
    private Integer type; // 0 代表点的是炒菜，1 代表的点的是火锅用食材
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public CookOrderResponse toResp(){
        CookOrderResponse cookOrderResponse = new CookOrderResponse();
        BeanUtils.copyProperties(this, cookOrderResponse);
        return cookOrderResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCookCode() {
        return cookCode;
    }

    public void setCookCode(String cookCode) {
        this.cookCode = cookCode;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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
}
