package com.luwh.we.app.dto.request;

/**
 * @author lu.wh
 * @date 2023/11/30 11/17/06
 * @description
 */
public class OrderFoodRequest {
    private String cookCode;
    private String account;
    private String groupCode;
    private Integer type; // 0 是炒菜的点，1 是火锅食材的点

    public String getCookCode() {
        return cookCode;
    }

    public void setCookCode(String cookCode) {
        this.cookCode = cookCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
}
