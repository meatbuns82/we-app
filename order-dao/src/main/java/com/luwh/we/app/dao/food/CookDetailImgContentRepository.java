package com.luwh.we.app.dao.food;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.luwh.we.app.dao.BaseRepository;
import com.luwh.we.app.model.po.food.CookDetailImgContentPO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lu.wh
 * @date 2024/11/03 14/37/28
 * @description
 */
@Repository
public class CookDetailImgContentRepository implements BaseRepository<CookDetailImgContentPO> {
    private final CookDetailImgContentDao dao;

    public CookDetailImgContentRepository(CookDetailImgContentDao cookDetailImgContentDao) {
        this.dao = cookDetailImgContentDao;
    }

    @Override
    public CookDetailImgContentPO getById(String id) {
        return dao.selectById(id);
    }

    @Override
    public CookDetailImgContentPO get(LambdaQueryWrapper<CookDetailImgContentPO> wrapper) {
        return dao.selectOne(wrapper);
    }

    @Override
    public List<CookDetailImgContentPO> listAll() {
        return dao.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<CookDetailImgContentPO> list(LambdaQueryWrapper<CookDetailImgContentPO> wrapper) {
        return dao.selectList(wrapper);
    }

    @Override
    public void add(CookDetailImgContentPO cookDetailImgContentPO) {
        dao.insert(cookDetailImgContentPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBatch(List<CookDetailImgContentPO> t) {
        for (CookDetailImgContentPO cookDetailImgContentPO : t) {
            dao.insert(cookDetailImgContentPO);
        }
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(LambdaQueryWrapper<CookDetailImgContentPO> wrapper) {
        dao.delete(wrapper);
    }

    @Override
    public CookDetailImgContentPO updateById(String id, CookDetailImgContentPO cookDetailImgContentPO) {
        cookDetailImgContentPO.setId(Long.parseLong(id));
        dao.updateById(cookDetailImgContentPO);
        return dao.selectById(id);
    }

    @Override
    public CookDetailImgContentPO update(CookDetailImgContentPO cookDetailImgContentPO, LambdaQueryWrapper<CookDetailImgContentPO> wrapper) {
        int update = dao.update(cookDetailImgContentPO, wrapper);
        CookDetailImgContentPO updated = dao.selectOne(wrapper);
        return updated;
    }
}
