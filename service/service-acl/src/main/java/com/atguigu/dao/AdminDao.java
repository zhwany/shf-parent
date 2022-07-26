package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Admin;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/19 23:15
 */
public interface AdminDao extends BaseDao<Admin> {
    List<Admin> findAll();
}
