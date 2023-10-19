package com.luwh.we.app.dto.response;

import java.util.Date;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/10/13 11/30/52
 * @description
 */
public class CookDetailResponse {
    private Long id;
    private String cookName;
    private String cookCode;
    private String mainImgCode;
    private String mainImgPath;
    private List<CookStepResponse> step;
    private List<CookUsageFeesResponse> usageFee;
    private String tip;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainImgPath() {
        return mainImgPath;
    }

    public void setMainImgPath(String mainImgPath) {
        this.mainImgPath = mainImgPath;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public String getCookCode() {
        return cookCode;
    }

    public void setCookCode(String cookCode) {
        this.cookCode = cookCode;
    }

    public String getMainImgCode() {
        return mainImgCode;
    }

    public void setMainImgCode(String mainImgCode) {
        this.mainImgCode = mainImgCode;
    }

    public List<CookStepResponse>  getStep() {
        return step;
    }

    public void setStep(List<CookStepResponse> step) {
        this.step = step;
    }

    public List<CookUsageFeesResponse> getUsageFee() {
        return usageFee;
    }

    public void setUsageFee(List<CookUsageFeesResponse> usageFee) {
        this.usageFee = usageFee;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
