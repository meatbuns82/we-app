package com.luwh.we.app.dao.food;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luwh.we.app.model.po.food.FoodKindPO;

/**
 * @author lu.wh
 * @date 2023/09/25 15/26/35
 * @description
 */
@Mapper
public interface FoodKindDao extends BaseMapper<FoodKindPO> {
}
