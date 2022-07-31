package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;

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

    /**
     * 用户是否关注房源
     * @param userId
     * @param houseId
     * @return
     */
    Boolean isFollowed(Long userId, Long houseId);

    PageInfo<UserFollowVo> findListPage(Integer pageNum, Integer pageSize, Long userId);

    /**
     * 取消关注
     * @param id
     * @return
     */
    void cancelFollow(Long id);
}
