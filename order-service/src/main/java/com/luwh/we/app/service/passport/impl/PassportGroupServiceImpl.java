package com.luwh.we.app.service.passport.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.constants.SqlConstants;
import com.luwh.we.app.common.exception.exceptions.OrderException;
import com.luwh.we.app.core.util.UUIDGenerateUtil;
import com.luwh.we.app.dao.passport.PassportGroupDao;
import com.luwh.we.app.model.po.passport.PassportGroupPO;
import com.luwh.we.app.service.passport.PassportGroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/11/30 11/39/50
 * @description
 */
@Service
public class PassportGroupServiceImpl extends ServiceImpl<PassportGroupDao, PassportGroupPO> implements PassportGroupService {

    @Override
    public void addGroup(PassportGroupPO po) {
        check(po);
        po.setGroupCode(UUIDGenerateUtil.generateUniqueId());
        baseMapper.insert(po);
    }

    @Override
    public void updateGroup(PassportGroupPO po) {
        check(po);
        baseMapper.updateById(po);
    }

    @Override
    public void deleteGroup(Long groupCode) {
        LambdaQueryWrapper<PassportGroupPO> wrapper = queryWrapper();
        wrapper.eq(PassportGroupPO::getGroupCode, groupCode);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<PassportGroupPO> selectByCodes(List<String> groupCodes) {
        LambdaQueryWrapper<PassportGroupPO> wrapper = queryWrapper();
        if(CollectionUtils.isEmpty(groupCodes)){
            return new ArrayList<>();
        }
        wrapper.in(PassportGroupPO::getGroupCode, groupCodes);
        List<PassportGroupPO> passportGroupPOS = baseMapper.selectList(wrapper);
        return passportGroupPOS;
    }

    private void check(PassportGroupPO po){
        LambdaQueryWrapper<PassportGroupPO> wrapper = queryWrapper();
        wrapper.eq(PassportGroupPO::getGroupName, po.getGroupName());
        if(po.getId() != null){
            wrapper.ne(PassportGroupPO::getId, po.getId());
        }
        wrapper.last(SqlConstants.SQL_LIMIT_ONE);
        PassportGroupPO passportGroupPO = baseMapper.selectOne(wrapper);
        if(passportGroupPO != null){
            throw new OrderException("名称已经被占用了，换一个吧");
        }
    }
}
