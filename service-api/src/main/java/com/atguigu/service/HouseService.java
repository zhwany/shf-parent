package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.House;

/**
 * @Author mabo
 * @Date 2022/7/25 21:25
 */
public interface HouseService extends BaseService<House> {
    void publish(Long id, Integer status);
}
