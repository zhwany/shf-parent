package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.UserFollow;

/**
 * @Author mabo
 * @Date 2022/7/31 18:24
 */
public interface UserFollowService extends BaseService<UserFollow> {
    /**
     * 关注房源
     * @param userId
     * @param houseId
     */
    void follow(Long userId, Long houseId);
}
