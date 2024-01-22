package com.luwh.we.app.server.controller.passport;

import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.dto.request.ThirdAccountRequest;

/**
 * @author lu.wh
 * @date 2023/12/01 14/59/01
 * @description
 */
public interface PassportUnionService {
    ResponseResult thirdLogin(ThirdAccountRequest request);
}
