package com.luwh.we.app.dto.request;

/**
 * @author lu.wh
 * @date 2023/12/04 14/57/08
 * @description
 */
public class CookOrderCollectRequest {
    private String account;
    private String cookCode;

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
}
