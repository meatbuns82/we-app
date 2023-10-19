package com.luwh.we.app.model.po.food;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author lu.wh
 * @date 2023/09/25 15/56/48
 * @description
 */
@TableName("cook_detail_img_content")
public class CookDetailImgContentPO {
    @TableField(value = "id")
    private Long id;
    private String imgCode;
    private String imgBlob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    public String getImgBlob() {
        return imgBlob;
    }

    public void setImgBlob(String imgBlob) {
        this.imgBlob = imgBlob;
    }
}
