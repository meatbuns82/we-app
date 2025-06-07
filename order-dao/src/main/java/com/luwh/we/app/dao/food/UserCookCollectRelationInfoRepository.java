package com.luwh.we.app.dao.food;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.luwh.we.app.dao.BaseRepository;
import com.luwh.we.app.model.po.food.UserCookCollectRelationInfoPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lu.wh
 * @date 2024/11/03 14/38/51
 * @description
 */
@Repository
public class UserCookCollectRelationInfoRepository implements BaseRepository<UserCookCollectRelationInfoPO> {
    private UserCookCollectRelationInfoDao dao;

    public UserCookCollectRelationInfoRepository(UserCookCollectRelationInfoDao dao) {
        this.dao = dao;
    }

    @Override
    public UserCookCollectRelationInfoPO getById(String id) {
        return dao.selectById(id);
    }

    @Override
    public UserCookCollectRelationInfoPO get(LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper) {
        return dao.selectOne(wrapper);
    }

    @Override
    public List<UserCookCollectRelationInfoPO> listAll() {
        return dao.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<UserCookCollectRelationInfoPO> list(LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper) {
        return dao.selectList(wrapper);
    }

    @Override
    public void add(UserCookCollectRelationInfoPO cookDetailPO) {
        dao.insert(cookDetailPO);
    }

    @Override
    public void addBatch(List<UserCookCollectRelationInfoPO> t) {
        for (UserCookCollectRelationInfoPO po : t) {
            dao.insert(po);
        }
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper) {
        dao.delete(wrapper);
    }

    @Override
    public UserCookCollectRelationInfoPO updateById(String id, UserCookCollectRelationInfoPO po) {
        po.setId(Long.parseLong(id));
        dao.updateById(po);
        return dao.selectById(id);
    }

    @Override
    public UserCookCollectRelationInfoPO update(UserCookCollectRelationInfoPO po, LambdaQueryWrapper<UserCookCollectRelationInfoPO> wrapper) {
        int update = dao.update(po, wrapper);
        UserCookCollectRelationInfoPO updated = dao.selectOne(wrapper);
        return updated;
    }
}
