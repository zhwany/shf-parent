package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/19 0:39
 */
public interface RoleDao extends BaseDao<Role> {

    List<Role> findAll();
}

//改造前
//public interface RoleDao {
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
//    Page<Role> findPage(Map<String, Object> filters);
//}

