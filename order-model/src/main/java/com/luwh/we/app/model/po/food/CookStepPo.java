package com.luwh.we.app.model.po.food;

/**
 * 这个结构体没有对应的表，而是 cook_detail表的 cook_step字段的结构体
 *[{"num": 1, "stepDesc": ["备齐食材，红薯粉提前泡一晚上"], "imgCode": "qodgj394"}]
 * @author lu.wh
 * @date 2023/09/25 15/58/08
 * @description
 */
public class CookStepPo {
    private Integer num;
    private String stepDesc;
    private String imgCode;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }
}
