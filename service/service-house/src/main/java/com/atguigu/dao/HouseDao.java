package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.House;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @Author mabo
 * @Date 2022/7/25 19:20
 */
public interface HouseDao extends BaseDao<House> {
    Page<HouseVo> findListPage(@Param("vo") HouseQueryVo houseQueryVo);
}
