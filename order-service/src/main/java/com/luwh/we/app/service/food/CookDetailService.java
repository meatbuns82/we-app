package com.luwh.we.app.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.dto.response.CookDetailResponse;
import com.luwh.we.app.model.po.food.CookDetailPO;
import com.luwh.we.app.service.BaseService;

/**
 * @author lu.wh
 * @date 2023/09/27 10/40/21
 * @description
 */
public interface CookDetailService extends IService<CookDetailPO>, BaseService<CookDetailPO> {

    CookDetailResponse selectCookCode(String cookCode);
}
