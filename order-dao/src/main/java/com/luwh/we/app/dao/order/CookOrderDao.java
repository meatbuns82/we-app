package com.luwh.we.app.dao.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luwh.we.app.model.po.order.CookOrderPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lu.wh
 * @date 2023/11/30 11/36/45
 * @description
 */
@Mapper
public interface CookOrderDao extends BaseMapper<CookOrderPO> {
}
