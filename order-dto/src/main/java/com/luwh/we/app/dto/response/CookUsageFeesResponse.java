package com.luwh.we.app.dto.response;

/**
 * 这个结构体没有对应的表，而是 cook_detail表的 usage_fees的结构体
 *[{"usageFeeName": "粉条", "usageFeeNum": "四小棵"}]
 * @author lu.wh
 * @date 2023/09/25 15/59/20
 * @description
 */
public class CookUsageFeesResponse {
    private String usageFeeName;
    private String usageFeeNum;

    public String getUsageFeeName() {
        return usageFeeName;
    }

    public void setUsageFeeName(String usageFeeName) {
        this.usageFeeName = usageFeeName;
    }

    public String getUsageFeeNum() {
        return usageFeeNum;
    }

    public void setUsageFeeNum(String usageFeeNum) {
        this.usageFeeNum = usageFeeNum;
    }
}
