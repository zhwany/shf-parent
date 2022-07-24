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

//改造前
//public interface RoleService {
//    List<Role> findAll();
//
//    Integer insert(Role role);
//
//    Role getById(Long id);
//
//    Integer update(Role role);
//
//    void delete(Long id);
//
//    PageInfo<Role> findPage(Map<String, Object> filters);
//}