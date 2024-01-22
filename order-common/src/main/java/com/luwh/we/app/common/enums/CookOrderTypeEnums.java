package com.luwh.we.app.common.enums;

import java.util.Arrays;

/**
 * @author lu.wh
 * @date 2023/12/04 16/39/06
 * @description
 */
public enum CookOrderTypeEnums {

    GOOD(0, "good"),
    BAD(1, "bad"),
    COLLECT(2, "collect")
    ;

    private Integer type;
    private String typeDesc;

    CookOrderTypeEnums(Integer type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }

    public static CookOrderTypeEnums fromVal(Integer type){
        return Arrays.asList(values()).stream().filter(e -> e.getType().equals(type)).findFirst().orElse(null);
    }

    public Integer getType() {
        return type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }
}
