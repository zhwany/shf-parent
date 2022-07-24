package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/19 0:40
 */

public interface RoleService extends BaseService<Role> {
    List<Role> findAll();
}