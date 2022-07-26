package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/26 0:57
 */
public interface HouseImageDao extends BaseDao<HouseImage> {
    List<HouseImage> findList(@Param("houseId") Long houseId, @Param("type") Integer type);
}
