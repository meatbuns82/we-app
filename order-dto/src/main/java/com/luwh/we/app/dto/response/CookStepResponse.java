package com.luwh.we.app.dto.response;

import java.util.List;

/**
 * 这个结构体没有对应的表，而是 cook_detail表的 cook_step字段的结构体
 *[{"num": 1, "stepDesc": ["备齐食材，红薯粉提前泡一晚上"], "imgCode": "qodgj394"}]
 * @author lu.wh
 * @date 2023/09/25 15/58/08
 * @description
 */
public class CookStepResponse {
    private Integer num;
    private List<String> stepDesc;
    private String imgCode;
    private String imgPath;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<String> getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(List<String> stepDesc) {
        this.stepDesc = stepDesc;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
