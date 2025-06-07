package com.luwh.we.app.dao.food;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.luwh.we.app.dao.BaseRepository;
import com.luwh.we.app.model.po.food.CookDetailPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lu.wh
 * @date 2024/11/03 14/36/43
 * @description
 */
@Repository
public class CookDetailRepository implements BaseRepository<CookDetailPO> {
    private CookDetailDao dao;

    public CookDetailRepository(CookDetailDao dao) {
        this.dao = dao;
    }

    @Override
    public CookDetailPO getById(String id) {
        return dao.selectById(id);
    }

    @Override
    public CookDetailPO get(LambdaQueryWrapper<CookDetailPO> wrapper) {
        return dao.selectOne(wrapper);
    }

    @Override
    public List<CookDetailPO> listAll() {
        return dao.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<CookDetailPO> list(LambdaQueryWrapper<CookDetailPO> wrapper) {
        return dao.selectList(wrapper);
    }

    @Override
    public void add(CookDetailPO cookDetailPO) {
        dao.insert(cookDetailPO);
    }

    @Override
    public void addBatch(List<CookDetailPO> t) {
        for (CookDetailPO po : t) {
            dao.insert(po);
        }
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(LambdaQueryWrapper<CookDetailPO> wrapper) {
        dao.delete(wrapper);
    }

    @Override
    public CookDetailPO updateById(String id, CookDetailPO po) {
        po.setId(Long.parseLong(id));
        dao.updateById(po);
        return dao.selectById(id);
    }

    @Override
    public CookDetailPO update(CookDetailPO po, LambdaQueryWrapper<CookDetailPO> wrapper) {
        int update = dao.update(po, wrapper);
        CookDetailPO updated = dao.selectOne(wrapper);
        return updated;
    }
}
