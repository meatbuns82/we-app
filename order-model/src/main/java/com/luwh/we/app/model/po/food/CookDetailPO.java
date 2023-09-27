package com.luwh.we.app.model.po.food;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

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
    @TableField(exist = false)
    private CookStepPo step;
    @TableField(exist = false)
    private CookUsageFeesPO usageFee;
    private String tip;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

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


    public void setStep(CookStepPo step) {
        this.step = step;
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
