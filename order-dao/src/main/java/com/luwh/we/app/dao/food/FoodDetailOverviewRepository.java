package com.luwh.we.app.dao.food;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luwh.we.app.dao.BaseRepository;
import com.luwh.we.app.model.po.food.FoodDetailOverviewPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lu.wh
 * @date 2024/11/03 14/37/48
 * @description
 */
@Repository
public class FoodDetailOverviewRepository implements BaseRepository<FoodDetailOverviewPO> {
    private FoodDetailOverviewDao dao;

    @Override
    public FoodDetailOverviewPO getById(String id) {
        return dao.selectById(id);
    }

    @Override
    public FoodDetailOverviewPO get(QueryWrapper<FoodDetailOverviewPO> wrapper) {
        return dao.selectOne(wrapper);
    }

    @Override
    public List<FoodDetailOverviewPO> listAll() {
        return dao.selectList(new QueryWrapper<>());
    }

    @Override
    public List<FoodDetailOverviewPO> list(QueryWrapper<FoodDetailOverviewPO> wrapper) {
        return dao.selectList(wrapper);
    }

    @Override
    public void add(FoodDetailOverviewPO cookDetailPO) {
        dao.insert(cookDetailPO);
    }

    @Override
    public void addBatch(List<FoodDetailOverviewPO> t) {
        for (FoodDetailOverviewPO po : t) {
            dao.insert(po);
        }
    }

    @Override
    public void deleteById(String id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteById(QueryWrapper<FoodDetailOverviewPO> wrapper) {
        dao.delete(wrapper);
    }

    @Override
    public FoodDetailOverviewPO updateById(String id, FoodDetailOverviewPO po) {
        po.setId(Long.parseLong(id));
        dao.updateById(po);
        return dao.selectById(id);
    }

    @Override
    public FoodDetailOverviewPO update(FoodDetailOverviewPO po, QueryWrapper<FoodDetailOverviewPO> wrapper) {
        int update = dao.update(po, wrapper);
        FoodDetailOverviewPO updated = dao.selectOne(wrapper);
        return updated;
    }
}