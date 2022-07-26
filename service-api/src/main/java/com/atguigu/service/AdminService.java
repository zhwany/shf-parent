package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Admin;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/19 23:17
 */
public interface AdminService extends BaseService<Admin> {
    List<Admin> findAll();
}