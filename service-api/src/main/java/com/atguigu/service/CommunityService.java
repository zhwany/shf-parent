package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Community;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/25 0:32
 */
public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}
