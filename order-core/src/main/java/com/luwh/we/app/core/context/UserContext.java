package com.luwh.we.app.core.context;

import com.luwh.we.app.model.po.passport.PassportUserPO;

import java.util.Optional;

/**
 * @author lu.wh
 * @date 2023/09/25 15/36/41
 * @description
 */
public class UserContext {
    private static final UserContext context =  new UserContext();
    private static final ThreadLocal<PassportUserPO> tl = new ThreadLocal();

    public static UserContext getInstance(){
        return context;
    }

    public void set(PassportUserPO po){
        tl.set(po);
    }

    public String getUser(){

        return  Optional.ofNullable(tl.get()).orElse(new PassportUserPO()).getAccount();
    }
}
