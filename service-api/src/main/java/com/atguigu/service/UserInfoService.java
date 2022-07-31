package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.UserInfo;

/**
 * @Author mabo
 * @Date 2022/7/29 20:04
 */
public interface UserInfoService extends BaseService<UserInfo> {
    UserInfo getByPhone(String phone);
}
