package com.luwh.we.app.service.food;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.model.po.food.FoodKindPO;

/**
 * @author lu.wh
 * @date 2023/09/25 15/20/27
 * @description
 */
public interface FoodKindService extends IService<FoodKindPO> {

    /**
     * 查询食物的种类
     * 
     * @return
     */
    List<String> selectAllFoodKind(String search);

    /**
     * 查询所有的食物，分页
     * @return
     */
    Page<FoodKindPO> selectFoodPage(Integer page, Integer pageSize, String search);
}
