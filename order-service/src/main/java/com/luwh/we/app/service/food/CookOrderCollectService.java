package com.luwh.we.app.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwh.we.app.model.po.food.UserCookCollectRelationInfoPO;
import com.luwh.we.app.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author lu.wh
 * @date 2023/12/04 14/49/43
 * @description
 */
public interface CookOrderCollectService extends IService<UserCookCollectRelationInfoPO>, BaseService<UserCookCollectRelationInfoPO> {
    /**
     * 点赞食物
     *
     * @param cookCode
     * @param account
     */
    void goodCook(String cookCode, String account);

    /**
     * 点菜食物
     *
     * @param cookCode
     * @param account
     */
    void badCook(String cookCode, String account);

    /**
     * 收藏食物
     *
     * @param cookCode
     * @param account
     */
    void collectCook(String cookCode, String account);

    /**
     * 统计某个食物的各个状态的数量
     * @param cookCodes
     * @return
     */
    Map<String, Map<String, Integer>> countTypeByCookCodes(List<String> cookCodes);
}
