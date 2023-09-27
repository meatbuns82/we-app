package com.luwh.we.app.model.po.food;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/09/25 15/48/14
 * @description
 */
@TableName("food_detail_overview")
public class FoodDetailOverviewPO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String cookDetailName;
    private String cookCode;
    private List<String> cookIngredient; // 菜品的成份
    private byte[] img;
    private String foodCode;
    private Boolean enable;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
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
}
