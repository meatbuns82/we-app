package com.luwh.we.app.model.po.food;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author lu.wh
 * @date 2023/12/05 15/38/26
 * @description
 */
@TableName("food_img_content")
public class FoodImgContentPO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String foodCode;
    private String imgBlob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getImgBlob() {
        return imgBlob;
    }

    public void setImgBlob(String imgBlob) {
        this.imgBlob = imgBlob;
    }
}
