package com.luwh.we.app.dto.response;

import java.util.Date;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/10/12 14/58/51
 * @description
 */
public class FoodDetailOverviewResponse {

    private Long id;
    private String cookDetailName;
    private String cookCode;
    private List<String> cookIngredient; // 菜品的成份
    private String foodCode;
    private Boolean enable;
    private Date createTime;
    private String picturePath;
    private String foodType;
    private String foodTypeLabel;

    private Integer goodCount;
    private Integer badCount;
    private Integer collectCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCookDetailName() {
        return cookDetailName;
    }

    public void setCookDetailName(String cookDetailName) {
        this.cookDetailName = cookDetailName;
    }

    public String getCookCode() {
        return cookCode;
    }

    public void setCookCode(String cookCode) {
        this.cookCode = cookCode;
    }

    public List<String> getCookIngredient() {
        return cookIngredient;
    }

    public void setCookIngredient(List<String> cookIngredient) {
        this.cookIngredient = cookIngredient;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodTypeLabel() {
        return foodTypeLabel;
    }

    public void setFoodTypeLabel(String foodTypeLabel) {
        this.foodTypeLabel = foodTypeLabel;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getBadCount() {
        return badCount;
    }

    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }
}
