package com.luwh.we.app.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.model.po.food.CookDetailImgContentPO;
import com.luwh.we.app.service.BaseService;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/10/13 11/41/06
 * @description
 */
public interface CookDetailImgContentService extends
        IService<CookDetailImgContentPO>, BaseService<CookDetailImgContentPO> {
    List<CookDetailImgContentPO> selectCookDetailByCookCode(List<String> imgCode);
}
