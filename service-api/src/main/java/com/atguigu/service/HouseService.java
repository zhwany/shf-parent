package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.House;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author mabo
 * @Date 2022/7/25 21:25
 */
public interface HouseService extends BaseService<House> {
    void publish(Long id, Integer status);

    /**
     * 用于前端分页展示房源信息
     * @param pageNum
     * @param pageSize
     * @param houseQueryVo
     * @return
     */
    PageInfo<HouseVo> findListPage(int pageNum, int pageSize, HouseQueryVo houseQueryVo);
}
