package com.luwh.we.app.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
 * @author lu.wh
 * @date 2024/11/03 14/39/27
 * @description
 */
public interface BaseRepository<T> {
    // Get
    public T getById(String id);

    public T get(LambdaQueryWrapper<T> wrapper);

    public List<T> listAll();

    public List<T> list(LambdaQueryWrapper<T> wrapper);

    // Add
    public void add(T t);

    public void addBatch(List<T> t);

    // Delete
    public void delete(String id);

    public void delete(LambdaQueryWrapper<T> wrapper);

    // Update
    public T updateById(String id, T t);

    public T update(T t, LambdaQueryWrapper<T> wrapper);
}
