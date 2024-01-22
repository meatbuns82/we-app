package com.luwh.we.app.core.web.enums;

/**
 * @author lu.wh
 * @date 2023/10/22 14/46/19
 * @description
 */
public enum ResponseEnums {
    ERROR(500, "执行错误", "Normal error"),
    NETWORK_ERROR(502, "网络错误", "network error"),
    ABSENT_PARAMS_ERROR(505, "缺少参数", "lack off params error"),
    ;
    private Integer code;
    private String message;
    private String desc;

     ResponseEnums(Integer code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }

    public String desc() {
        return desc;
    }
}
