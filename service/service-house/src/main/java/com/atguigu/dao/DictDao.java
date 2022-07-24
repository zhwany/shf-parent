package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Dict;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/24 22:52
 */
public interface DictDao extends BaseDao<Dict> {
    List<Dict> findListByParentId(Long parentId);
    //判断一个节点的孩子的数量，从而判断该节点是否是父节点
    Integer countIsParent(Long id);

    Dict getByDictCode(String dictCode);

    String getNameById(Long areaId);
}
