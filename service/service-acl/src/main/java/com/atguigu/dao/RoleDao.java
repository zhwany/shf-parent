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

