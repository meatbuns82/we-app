package com.luwh.we.app.service.vote;

/**
 * @author lu.wh
 * @date 2023/11/08 14/21/15
 * @description
 */
public interface VoteService {
    void vote(String food);

    void publish(String food);

    void foodOrder(Integer start, Integer end);

    /**
     *
     * @param food 食物
     * @param group 分组
     * @param increase true, 增加， false, 减少
     */
    void classifyFood(String food, String group, boolean increase);

    public void getGroupFood(String group);
}
