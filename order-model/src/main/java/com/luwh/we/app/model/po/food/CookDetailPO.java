package com.luwh.we.app.model.po.food;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.luwh.we.app.dto.response.CookDetailResponse;
import com.luwh.we.app.dto.response.CookStepResponse;
import com.luwh.we.app.dto.response.CookUsageFeesResponse;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/09/25 15/56/32
 * @description
 */
@TableName("cook_detail")
public class CookDetailPO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String cookName;
    private String cookCode;
    private String mainImgCode;
    private String cookStep;
    private String usageFees;
    private String tip;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public CookDetailResponse toResp(){
        CookDetailResponse response = new CookDetailResponse();
        BeanUtils.copyProperties(this, response);
        List<CookStepResponse> stepResponse = JSONObject.parseObject(this.cookStep, new TypeReference<List<CookStepResponse>>(){});
        List<CookUsageFeesResponse> usageFeesResponse = JSONObject.parseObject(this.usageFees, new TypeReference<List<CookUsageFeesResponse>>(){});
        response.setStep(stepResponse);
        response.setUsageFee(usageFeesResponse);
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCookStep() {
        return cookStep;
    }

    public void setCookStep(String cookStep) {
        this.cookStep = cookStep;
    }

    public String getUsageFees() {
        return usageFees;
    }

    public void setUsageFees(String usageFees) {
        this.usageFees = usageFees;
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
