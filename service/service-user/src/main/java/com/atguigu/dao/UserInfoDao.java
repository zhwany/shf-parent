package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.UserInfo;

/**
 * @Author mabo
 * @Date 2022/7/29 20:03
 */
public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByPhone(String phone);
}
