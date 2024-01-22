package com.luwh.we.app.service.passport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.model.po.passport.PassportGroupPO;
import com.luwh.we.app.service.BaseService;

import java.util.List;

/**
 * @author lu.wh
 * @date 2023/11/30 11/39/37
 * @description
 */
public interface PassportGroupService extends IService<PassportGroupPO>, BaseService<PassportGroupPO> {

    void addGroup(PassportGroupPO po);

    void updateGroup(PassportGroupPO po);

    void deleteGroup(Long groupCode);

    List<PassportGroupPO> selectByCodes(List<String> groupCodes);
}
