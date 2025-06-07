package com.luwh.we.app.dao.food;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.luwh.we.app.dao.BaseRepository;
import com.luwh.we.app.model.po.food.FoodKindPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lu.wh
 * @date 2024/11/03 14/38/34
 * @description
 */
@Repository
public class FoodKindRepository implements BaseRepository<FoodKindPO> {
    private FoodKindDao dao;
    public FoodKindRepository(FoodKindDao dao) {
        this.dao = dao;
    }

    @Override
    public FoodKindPO getById(String id) {
        return dao.selectById(id);
    }

    @Override
    public FoodKindPO get(LambdaQueryWrapper<FoodKindPO> wrapper) {
        return dao.selectOne(wrapper);
    }

    @Override
    public List<FoodKindPO> listAll() {
        return dao.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<FoodKindPO> list(LambdaQueryWrapper<FoodKindPO> wrapper) {
        return dao.selectList(wrapper);
    }

    @Override
    public void add(FoodKindPO cookDetailPO) {
        dao.insert(cookDetailPO);
    }

    @Override
    public void addBatch(List<FoodKindPO> t) {
        for (FoodKindPO po : t) {
            dao.insert(po);
        }
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(LambdaQueryWrapper<FoodKindPO> wrapper) {
        dao.delete(wrapper);
    }

    @Override
    public FoodKindPO updateById(String id, FoodKindPO po) {
        po.setId(Long.parseLong(id));
        dao.updateById(po);
        return dao.selectById(id);
    }

    @Override
    public FoodKindPO update(FoodKindPO po, LambdaQueryWrapper<FoodKindPO> wrapper) {
        int update = dao.update(po, wrapper);
        FoodKindPO updated = dao.selectOne(wrapper);
        return updated;
    }
}
