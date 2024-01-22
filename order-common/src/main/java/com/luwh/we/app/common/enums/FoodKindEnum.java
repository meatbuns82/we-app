package com.luwh.we.app.common.enums;

import java.util.Arrays;

/**
 * @author lu.wh
 * @date 2023/11/29 15/10/14
 * @description
 */
public enum FoodKindEnum {
    MEAT_FOOD("meatFood", "荤"),
    VEGETABLE_FOOD("vegetableFood", "素"),
    STABLE_FOOD("stapleFood", "主食"),
    WATER_FOOD("waterFood", "水产"),
    ;
    private String foodType;
    private String foodTypeLabel;

    FoodKindEnum(String foodType, String foodTypeLabel) {
        this.foodType = foodType;
        this.foodTypeLabel = foodTypeLabel;
    }

    public static FoodKindEnum fromVal(String foodType){
        return Arrays.asList(values()).stream().filter(e -> e.getFoodType().equals(foodType)).findFirst().orElse(null);
    }

    public String getFoodType() {
        return foodType;
    }

    public String getFoodTypeLabel() {
        return foodTypeLabel;
    }
}
