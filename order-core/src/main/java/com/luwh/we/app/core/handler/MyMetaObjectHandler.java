package com.luwh.we.app.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.luwh.we.app.common.context.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 元数据的一些通用属性的设置
 *
 * @author lu.wh
 * @date 2023/09/25 15/34/44
 * @description
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        String user = UserContext.getInstance().getUser();
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createUser", String.class, user);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String user = UserContext.getInstance().getUser();
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateUser", String.class, user);
    }
}
