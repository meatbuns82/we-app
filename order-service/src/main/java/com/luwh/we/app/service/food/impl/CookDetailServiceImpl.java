package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwh.we.app.common.constants.Constants;
import com.luwh.we.app.common.constants.SqlConstants;
import com.luwh.we.app.core.cache.PictureCacheManager;
import com.luwh.we.app.dao.food.CookDetailDao;
import com.luwh.we.app.dto.response.CookDetailResponse;
import com.luwh.we.app.dto.response.CookStepResponse;
import com.luwh.we.app.model.po.food.CookDetailImgContentPO;
import com.luwh.we.app.model.po.food.CookDetailPO;
import com.luwh.we.app.service.food.CookDetailImgContentService;
import com.luwh.we.app.service.food.CookDetailService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lu.wh
 * @date 2023/10/13 11/16/36
 * @description
 */
@Service
public class CookDetailServiceImpl extends ServiceImpl<CookDetailDao, CookDetailPO> implements CookDetailService {
    @Resource
    private CookDetailImgContentService cookDetailImgContentService;
    @Resource
    private PictureCacheManager pictureCacheManager;
    @Override
    public CookDetailResponse selectCookCode(String cookCode) {
        LambdaQueryWrapper<CookDetailPO> wrapper = queryWrapper();
        wrapper.eq(CookDetailPO::getCookCode, cookCode);
        wrapper.last(SqlConstants.SQL_LIMIT_ONE);
        CookDetailPO cookDetailPO = baseMapper.selectOne(wrapper);
        if(cookDetailPO == null){
            return null;
        }
        CookDetailResponse cookDetailResponse = cookDetailPO.toResp();
        fillMainImage(cookDetailResponse, cookDetailPO.getMainImgCode());
        fillStepImage(cookDetailResponse.getStep());
        return cookDetailResponse;
    }

    private void fillMainImage(CookDetailResponse cookDetailResponse, String mainImgCode) {
        List<CookDetailImgContentPO> imgContentPOS = cookDetailImgContentService.selectCookDetailByCookCode(Arrays.asList(mainImgCode));
        if(CollectionUtils.isEmpty(imgContentPOS)){
            return;
        }
        CookDetailImgContentPO cookDetailImgContentPO = imgContentPOS.get(0);
        String path = blobToBase64AndWriteToLocal(mainImgCode, cookDetailImgContentPO.getImgBlob());
        cookDetailResponse.setMainImgPath(path);
    }

    private void fillStepImage(List<CookStepResponse> step) {
        if(CollectionUtils.isEmpty(step)){
            return;
        }
        Map<String, CookStepResponse> stepCache = new HashMap<>();
        List<String> imgCode = step.stream().map(e ->{
                    stepCache.put(e.getImgCode(), e);
                    return e.getImgCode();
                }).collect(Collectors.toList());
        List<CookDetailImgContentPO> cookDetailImgContentPOS = cookDetailImgContentService.selectCookDetailByCookCode(imgCode);
        for (CookDetailImgContentPO cookDetailImgContentPO : cookDetailImgContentPOS) {
            String key = cookDetailImgContentPO.getImgCode();
//            byte[] base64Img = Base64.getDecoder().decode(cookDetailImgContentPO.getImgBlob().replace("b'", "").replace("'", ""));
//            String path = pictureCacheManager.writeToLocalAndCache(key, base64Img, Constants.FILE_SUFFIX_JPEG);
            String path = blobToBase64AndWriteToLocal(key, cookDetailImgContentPO.getImgBlob());
            CookStepResponse cookStepResponse = stepCache.get(key);
            cookStepResponse.setImgPath(path);
        }
    }

    private String blobToBase64AndWriteToLocal(String key, String blob){
        byte[] base64Img = Base64.getDecoder().decode(blob.replace("b'", "").replace("'", ""));
        String path = pictureCacheManager.writeToLocalAndCache(key, base64Img, Constants.FILE_SUFFIX_JPEG);
        return path;

    }
}
