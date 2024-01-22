package com.luwh.we.app.common.constants;

/**
 * @author lu.wh
 * @date 2023/11/08 14/11/34
 * @description
 */
public class VoteConstants {
    public static final Long ONE_WEEK_IN_SECONDS = 7 * 86400L; // 每次投票的截止时间， 这里应该自定义，现在是测试就写死了
    public static final Integer VOTE_SCORE = 432; // 每次投票的分值  每个物品的投票我们认为至少200个票，一天86400s 86400 / 200 = 432
}
