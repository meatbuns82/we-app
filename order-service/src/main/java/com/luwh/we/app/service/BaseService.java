package com.luwh.we.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

/**
 * @author lu.wh
 * @date 2023/09/25 17/35/22
 * @description
 */
public interface BaseService<T>{

    default LambdaQueryWrapper<T> queryWrapper(){
        return new LambdaQueryWrapper<T>();
    }
    default LambdaUpdateWrapper<T> updateWrapper(){
        return new LambdaUpdateWrapper<>();
    }
}
