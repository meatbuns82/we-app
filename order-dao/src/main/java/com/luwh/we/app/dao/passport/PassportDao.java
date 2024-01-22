package com.luwh.we.app.dao.passport;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luwh.we.app.model.po.passport.PassportUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lu.wh
 * @date 2023/10/19 17/29/06
 * @description
 */
@Mapper
public interface PassportDao extends BaseMapper<PassportUserPO> {
}
