package com.luwh.we.app.dao.food;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.luwh.we.app.dao.BaseRepository;
import com.luwh.we.app.model.po.food.FoodImgContentPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lu.wh
 * @date 2024/11/03 14/38/09
 * @description
 */
@Repository
public class FoodImgContentRepository implements BaseRepository<FoodImgContentPO> {
    private FoodImgContentDao dao;

    public FoodImgContentRepository(FoodImgContentDao dao) {
        this.dao = dao;
    }

    @Override
    public FoodImgContentPO getById(String id) {
        return dao.selectById(id);
    }

    @Override
    public FoodImgContentPO get(LambdaQueryWrapper<FoodImgContentPO> wrapper) {
        return dao.selectOne(wrapper);
    }

    @Override
    public List<FoodImgContentPO> listAll() {
        return dao.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<FoodImgContentPO> list(LambdaQueryWrapper<FoodImgContentPO> wrapper) {
        return dao.selectList(wrapper);
    }

    @Override
    public void add(FoodImgContentPO cookDetailPO) {
        dao.insert(cookDetailPO);
    }

    @Override
    public void addBatch(List<FoodImgContentPO> t) {
        for (FoodImgContentPO po : t) {
            dao.insert(po);
        }
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(LambdaQueryWrapper<FoodImgContentPO> wrapper) {
        dao.delete(wrapper);
    }

    @Override
    public FoodImgContentPO updateById(String id, FoodImgContentPO po) {
        po.setId(Long.parseLong(id));
        dao.updateById(po);
        return dao.selectById(id);
    }

    @Override
    public FoodImgContentPO update(FoodImgContentPO po, LambdaQueryWrapper<FoodImgContentPO> wrapper) {
        int update = dao.update(po, wrapper);
        FoodImgContentPO updated = dao.selectOne(wrapper);
        return updated;
    }
}
