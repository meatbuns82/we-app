package com.luwh.we.app.service.passport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.model.po.passport.UserGroupRelationInfoPO;
import com.luwh.we.app.service.BaseService;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/11/30 12/02/07
 * @description
 */
public interface UserGroupRelationInfoService extends IService<UserGroupRelationInfoPO>, BaseService<UserGroupRelationInfoPO> {
    /**
     * 分配账号到组
     *
     * @param groupId
     * @param account
     */
    void assignToGroup(UserGroupRelationInfoPO infoPO);

    /**
     * 把账号从组里解绑
     *
     * @param groupId
     * @param account
     */
    void unassignFromGroup(String groupCode, String account);

    /**
     * 查找这个账号最经常访问的那一个组
     *
     * @param account
     */
    UserGroupRelationInfoPO selectMostFrequentlyVisitGroup(String account);

    /**
     * 用户每次打开一次组都应该触发一次访问组的动作，然后把访问次数 +1
     *
     * @param account
     * @param groupCode
     */
    void visitGroup(String account, String groupCode);

    /**
     * 查询这个账户下有哪些组
     * @param account
     */
    List<UserGroupRelationInfoPO> selectAllGroupByAccount(String account);
}
