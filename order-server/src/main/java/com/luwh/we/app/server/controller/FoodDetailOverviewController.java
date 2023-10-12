package com.luwh.we.app.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwh.we.app.common.constants.Constants;
import com.luwh.we.app.core.annoa.ApiDesc;
import com.luwh.we.app.core.annoa.ApiModelDesc;
import com.luwh.we.app.core.cache.PictureCacheManager;
import com.luwh.we.app.core.web.ResponsePageResult;
import com.luwh.we.app.core.web.ResponseResult;
import com.luwh.we.app.dto.response.FoodDetailOverviewResponse;
import com.luwh.we.app.model.po.food.FoodDetailOverviewPO;
import com.luwh.we.app.service.food.FoodDetailOverviewService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author lu.wh
 * @date 2023/09/27 10/35/24
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/food/detail")
@ApiDesc(desc = "食物细节的预览")
public class FoodDetailOverviewController {
    @Resource
    private FoodDetailOverviewService detailOverviewService;
    @Resource
    private PictureCacheManager pictureCacheManager;
    @ApiModelDesc(desc = "查询食物的种类")
    @GetMapping("/overview/page")
    public ResponsePageResult selectFoodDetailOverview(@RequestParam("page") Integer page,
                                                    @RequestParam("pageSize") Integer pageSize,
                                                    @RequestParam(value = "foodCode", required = false) String foodCode,
                                                    @RequestParam(value = "search", required = false) String search) {
        Page<FoodDetailOverviewPO> pageRes = detailOverviewService.selectFoodDetailOverviewPage(page, pageSize, foodCode, search);
        // 转换
        List<FoodDetailOverviewPO> records = pageRes.getRecords();
        records = records == null ? new ArrayList<>() : records;
        List<FoodDetailOverviewResponse> responses = new ArrayList<>();
        records.stream().parallel().forEach(record -> {
            FoodDetailOverviewResponse resp = record.toResp();
            byte[] base64Img = Base64.getDecoder().decode(record.getImg().replace("b'", "").replace("'", ""));
            String key = record.getCookCode();
            String path;
            if(!pictureCacheManager.cacheExist(key)) {
                path = pictureCacheManager.writeToLocalAndCache(key, base64Img, Constants.FILE_SUFFIX_JPEG);
            }else {
                path = pictureCacheManager.getLocalCachePath(key);
            }
            resp.setPicturePath(path);
            responses.add(resp);
        });
        return ResponsePageResult.success(pageRes.getCurrent(), pageRes.getSize(), pageRes.getTotal(), responses);
    }

    @ApiModelDesc(desc = "打开或关闭这个食物是否允许点单")
    @PutMapping("/status/change/{cookCode}")
    public ResponseResult foodSwitch(@PathVariable("cookCode") String cookCode) {
        detailOverviewService.foodSwitch(cookCode);
        return ResponseResult.success("切换成功");
    }
}
