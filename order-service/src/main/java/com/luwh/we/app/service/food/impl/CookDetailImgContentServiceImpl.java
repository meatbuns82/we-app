package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.dao.food.CookDetailImgContentDao;
import com.luwh.we.app.model.po.food.CookDetailImgContentPO;
import com.luwh.we.app.service.food.CookDetailImgContentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/10/13 11/46/09
 * @description
 */
@Service
public class CookDetailImgContentServiceImpl extends ServiceImpl<CookDetailImgContentDao, CookDetailImgContentPO>
        implements CookDetailImgContentService {

    @Override
    public List<CookDetailImgContentPO> selectCookDetailByCookCode(List<String> imgCode) {
        LambdaQueryWrapper<CookDetailImgContentPO> imgCodeWrapper = queryWrapper();
        if(!CollectionUtils.isEmpty(imgCode)){
            imgCodeWrapper.in(CookDetailImgContentPO::getImgCode, imgCode);
        }else {
            return new ArrayList<>();
        }
        List<CookDetailImgContentPO> cookDetailImgContentPOS = baseMapper.selectList(imgCodeWrapper);
        return cookDetailImgContentPOS;
    }
}
