package com.luwh.we.app.service.food.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwh.we.app.common.enums.CookOrderTypeEnums;
import com.luwh.we.app.common.enums.FoodKindEnum;
import com.luwh.we.app.core.cache.PictureCacheManager;
import com.luwh.we.app.dto.response.FoodDetailOverviewResponse;
import com.luwh.we.app.dto.response.FoodKindResponse;
import com.luwh.we.app.model.po.food.FoodDetailOverviewPO;
import com.luwh.we.app.model.po.food.FoodImgContentPO;
import com.luwh.we.app.model.po.food.FoodKindPO;
import com.luwh.we.app.service.food.CookOrderCollectService;
import com.luwh.we.app.service.food.FoodAndCookOverviewService;
import com.luwh.we.app.service.food.FoodDetailOverviewService;
import com.luwh.we.app.service.food.FoodImgContentService;
import com.luwh.we.app.service.food.FoodKindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lu.wh
 * @date 2023/11/29 14/51/42
 * @description
 */
@Service
public class FoodAndCookOverviewServiceImpl implements FoodAndCookOverviewService {
    @Resource
    private FoodDetailOverviewService detailOverviewService;
    @Resource
    private FoodKindService foodKindService;
    @Resource
    private PictureCacheManager pictureCacheManager;
    @Resource
    private CookOrderCollectService cookOrderCollectService;
    @Resource
    private FoodImgContentService foodImgContentService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<FoodDetailOverviewResponse> selectFoodDetailOverview(Integer page, Integer pageSize, String foodCode, String search) {
        Page<FoodDetailOverviewPO> pageRes = detailOverviewService.selectFoodDetailOverviewPage(page, pageSize, foodCode, search);
        // 转换
        List<FoodDetailOverviewPO> records = pageRes.getRecords();
        // 处理图片并填充一些必要属性
        List<FoodDetailOverviewResponse> responses = toResponse(records);
        Collections.sort(responses, new Comparator<FoodDetailOverviewResponse>() {
            @Override
            public int compare(FoodDetailOverviewResponse o1, FoodDetailOverviewResponse o2) {
                return o1.getCookDetailName().compareTo(o2.getCookDetailName());
            }
        });
        Page<FoodDetailOverviewResponse> objectPage = new Page<>(pageRes.getCurrent(), pageRes.getSize());
        objectPage.setTotal(pageRes.getTotal());
        objectPage.setRecords(responses);
        return objectPage;
    }

    @Override
    public Page<FoodDetailOverviewResponse> selectFoodDetailOverviewByCookCode(Integer page, Integer pageSize, List<String> cookCode) {
        Page<FoodDetailOverviewPO> pageRes = detailOverviewService.selectFoodDetailOverviewPageByCookCode(page, pageSize, cookCode);
        // 转换
        List<FoodDetailOverviewPO> records = pageRes.getRecords();
        // 处理图片并填充一些必要属性
        List<FoodDetailOverviewResponse> responses = toResponse(records);
        Collections.sort(responses, new Comparator<FoodDetailOverviewResponse>() {
            @Override
            public int compare(FoodDetailOverviewResponse o1, FoodDetailOverviewResponse o2) {
                return o1.getCookDetailName().compareTo(o2.getCookDetailName());
            }
        });
        Page<FoodDetailOverviewResponse> objectPage = new Page<>(pageRes.getCurrent(), pageRes.getSize());
        objectPage.setTotal(pageRes.getTotal());
        objectPage.setRecords(responses);
        return objectPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FoodDetailOverviewResponse>  selectFoodDetailList(List<String> cookCodes){
        List<FoodDetailOverviewPO> po = detailOverviewService.selectFoodDetailOverview(cookCodes);
        List<FoodDetailOverviewResponse> foodDetailOverviewResponses = toResponse(po);
        return foodDetailOverviewResponses;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<FoodKindResponse> selectFoodPage(Integer page, Integer pageSize, String search) {
        Page<FoodKindPO> foodKindPOPage = foodKindService.selectFoodPage(page, pageSize, search);
        List<FoodKindPO> records = foodKindPOPage.getRecords();

        // 填充图片地址
        HashMap<String, FoodKindResponse> hashMap = new HashMap<>();
        List<String> foodCodes = records.stream().map(e -> {
            FoodKindResponse foodKindResponse = e.toResp();
            String foodCode = e.getFoodCode();
            hashMap.put(foodCode, foodKindResponse);
            return  e.getFoodCode();
        }).collect(Collectors.toList());
        List<FoodImgContentPO> foodImgContents = foodImgContentService.selectFoodImgContentByFoodCodes(foodCodes);
        foodImgContents.stream().forEach(e -> {
            FoodKindResponse foodKindResponse = hashMap.get(e.getFoodCode());
            if(e.getImgBlob() != null) {
                String path = pictureCacheManager.cacheBase64(e.getFoodCode(), e.getImgBlob());
                foodKindResponse.setImagePath(path);
            }
        });
        List<FoodKindResponse> responses = hashMap.values().stream().collect(Collectors.toList());
        Page<FoodKindResponse> pageResult = new Page<>(foodKindPOPage.getCurrent(), foodKindPOPage.getSize());
        pageResult.setTotal(foodKindPOPage.getTotal());
        pageResult.setRecords(responses);
        return pageResult;
    }

    private List<FoodDetailOverviewResponse> toResponse(List<FoodDetailOverviewPO> records) {
        List<FoodDetailOverviewResponse> responses = new ArrayList<>();
        List<String> foodCodes = new ArrayList<>();
        cachePicture(records == null ? new ArrayList<>() : records, responses, foodCodes);
        // 为每个food detail 填充所属的食物类型
        fillFoodType(foodCodes, responses);
        List<String> cookCodes = responses.stream().map(e -> e.getCookCode()).collect(Collectors.toList());
        fillCollectInfo(cookCodes, responses);
        return responses;
    }

    private void fillCollectInfo(List<String> cookCodes, List<FoodDetailOverviewResponse> responses) {
        Map<String, Map<String, Integer>> result = cookOrderCollectService.countTypeByCookCodes(cookCodes);
        responses.stream().forEach(e -> {
            Map<String, Integer> countMap = result.get(e.getCookCode());
            Integer collectCount = countMap == null ? null : countMap.get(CookOrderTypeEnums.COLLECT.getTypeDesc());
            e.setCollectCount(collectCount == null ? 0 : collectCount);

            Integer badCount = countMap == null ? null : countMap.get(CookOrderTypeEnums.BAD.getTypeDesc());
            e.setBadCount(badCount == null ? 0 : badCount);

            Integer goodCount = countMap == null ? null : countMap.get(CookOrderTypeEnums.GOOD.getTypeDesc());
            e.setGoodCount(goodCount == null ? 0 : goodCount);
        });
    }


    /**
     * 缓存图片
     * @param records
     * @param responses
     * @param foodCodes
     */
    private void cachePicture(List<FoodDetailOverviewPO> records, List<FoodDetailOverviewResponse> responses, List<String> foodCodes){
        records.stream().parallel().forEach(record -> {
            // 将文件缓存到本地，并且写回路径，这样前端可以直接访问
            FoodDetailOverviewResponse resp = record.toResp();
            String path = pictureCacheManager.cacheBase64(record.getCookCode(), record.getImg());
            resp.setPicturePath(path);
            foodCodes.add(record.getFoodCode());
            responses.add(resp);
        });
    }

    /**
     * 填充食物类型
     *
     * @param foodCodes
     * @param responses
     */
    private void fillFoodType(List<String> foodCodes, List<FoodDetailOverviewResponse> responses) {
        List<FoodKindPO> foodKindPOS = foodKindService.selectFoodKindByFoodCodes(foodCodes);
        Map<String, String> foodTypes = new HashMap<>();
        foodKindPOS.stream().forEach(foodKind -> {
            foodTypes.put(foodKind.getFoodCode(), foodKind.getFoodType());
        });

        responses.stream().forEach(response -> {
            response.setFoodType(foodTypes.get(response.getFoodCode()));
            FoodKindEnum foodKindEnum = FoodKindEnum.fromVal(response.getFoodType());
            response.setFoodTypeLabel(foodKindEnum.getFoodTypeLabel());
        });
    }
}
