package com.luwh.we.app.service.passport.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.constants.SqlConstants;
import com.luwh.we.app.dao.passport.UserGroupRelationInfoDao;
import com.luwh.we.app.model.po.passport.UserGroupRelationInfoPO;
import com.luwh.we.app.service.passport.UserGroupRelationInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/11/30 12/02/46
 * @description
 */
@Service
public class UserGroupRelationInfoServiceImpl extends ServiceImpl<UserGroupRelationInfoDao, UserGroupRelationInfoPO>
        implements UserGroupRelationInfoService {

    @Override
    public void assignToGroup(UserGroupRelationInfoPO infoPO ) {
        infoPO.setVisitCount(1);
        baseMapper.insert(infoPO);
    }

    @Override
    public void unassignFromGroup(String groupCode, String account) {
        LambdaQueryWrapper<UserGroupRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserGroupRelationInfoPO::getAccount, account)
                .eq(UserGroupRelationInfoPO::getGroupCode, groupCode);
        baseMapper.delete(wrapper);
    }

    @Override
    public UserGroupRelationInfoPO selectMostFrequentlyVisitGroup(String account) {
        LambdaQueryWrapper<UserGroupRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserGroupRelationInfoPO::getAccount, account)
                .orderByAsc(UserGroupRelationInfoPO::getVisitCount)
                .last(SqlConstants.SQL_LIMIT_ONE);
        UserGroupRelationInfoPO userGroupRelationInfoPO = baseMapper.selectOne(wrapper);
        return userGroupRelationInfoPO;
    }

    @Override
    public void visitGroup(String account, String groupCode) {
        LambdaUpdateWrapper<UserGroupRelationInfoPO> wrapper = updateWrapper();
        wrapper.setSql("visit_count = visit_count + 1")
                .eq(UserGroupRelationInfoPO::getGroupCode, groupCode)
                .eq(UserGroupRelationInfoPO::getAccount, account);
        baseMapper.update(new UserGroupRelationInfoPO(), wrapper);
    }

    @Override
    public List<UserGroupRelationInfoPO> selectAllGroupByAccount(String account) {
        LambdaQueryWrapper<UserGroupRelationInfoPO> wrapper = queryWrapper();
        wrapper.eq(UserGroupRelationInfoPO::getAccount, account);
        List<UserGroupRelationInfoPO> userGroupRelationInfoPOS = baseMapper.selectList(wrapper);
        return userGroupRelationInfoPOS;
    }
}
