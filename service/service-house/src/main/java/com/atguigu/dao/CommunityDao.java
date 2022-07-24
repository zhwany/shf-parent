package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Community;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/25 0:31
 */
public interface CommunityDao extends BaseDao<Community> {
    List<Community> findAll();
}
