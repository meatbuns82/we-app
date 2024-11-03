package com.luwh.we.app.dto.response;

import java.util.Date;
import java.util.List;

/**
 * @author lu.wh
 * @date 2024/09/04 21/38/21
 * @description
 */
public class CookCollectResponse {
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
    private String account;

    public static CookCollectResponse fromPageFoodDetailOverviewResponse(FoodDetailOverviewResponse foodDetail, String account){
        CookCollectResponse response = new CookCollectResponse();
        response.setAccount(account);
        response.setId(foodDetail.getId());
        response.setCookDetailName(foodDetail.getCookDetailName());
        response.setCookCode(foodDetail.getCookCode());
        response.setCookIngredient(foodDetail.getCookIngredient());
        response.setFoodCode(foodDetail.getFoodCode());
        response.setEnable(foodDetail.getEnable());
        response.setCreateTime(foodDetail.getCreateTime());
        response.setPicturePath(foodDetail.getPicturePath());
        response.setFoodType(foodDetail.getFoodType());
        response.setFoodTypeLabel(foodDetail.getFoodTypeLabel());
        response.setGoodCount(foodDetail.getGoodCount());
        response.setBadCount(foodDetail.getBadCount());
        response.setCollectCount(foodDetail.getCollectCount());
        return response;
    }

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
