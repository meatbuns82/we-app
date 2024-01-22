package com.luwh.we.app.dto.response;

import cn.hutool.core.bean.BeanUtil;

import java.util.Date;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/11/30 16/43/25
 * @description
 */
public class CookOrderResponse {
    private Long id;
    private String cookCode;
    private String createUser;
    private String groupCode;
    private Integer type;
    private Date createTime;

    private Long cookId;
    private String cookDetailName;
    private List<String> cookIngredient; // 菜品的成份
    private String foodCode;
    private Boolean enable;

    private String foodName;
    private String foodKind;
    private String foodLink;
        private String imagePath;

    private String picturePath;
    private String foodType;
    private String foodTypeLabel;

    public void initFrom(FoodDetailOverviewResponse response){
        BeanUtil.copyProperties(response, this);
    }

    public void initFrom(FoodKindResponse response){
        BeanUtil.copyProperties(response, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCookCode() {
        return cookCode;
    }

    public void setCookCode(String cookCode) {
        this.cookCode = cookCode;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public String getCookDetailName() {
        return cookDetailName;
    }

    public void setCookDetailName(String cookDetailName) {
        this.cookDetailName = cookDetailName;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodKind() {
        return foodKind;
    }

    public void setFoodKind(String foodKind) {
        this.foodKind = foodKind;
    }

    public String getFoodLink() {
        return foodLink;
    }

    public void setFoodLink(String foodLink) {
        this.foodLink = foodLink;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
