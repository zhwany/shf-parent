package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.CommunityDao;
import com.atguigu.dao.DictDao;
import com.atguigu.entity.Community;
import com.atguigu.service.CommunityService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Author mabo
 * @Date 2022/7/25 0:33
 */
@Service(interfaceClass = CommunityService.class)
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {
    @Autowired
    private CommunityDao communityDao;
    @Autowired
    private DictDao dictDao;
    @Override
    protected BaseDao<Community> getEntityDao() {
        return communityDao;
    }

    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        int pageNum =  CastUtil.castInt(filters.get("pageNum"),1);
        int pageSize =  CastUtil.castInt(filters.get("pageSize"),5);
        //启用分页插件:
        PageHelper.startPage(pageNum,pageSize);
        //查询当前页的数据
        Page<Community> page =  getEntityDao().findPage(filters);
        //根据areaId和plateId获取areaName和plateName
        List<Community> communityList = page.getResult();
        for(Community community:communityList){
            String areaName = this.dictDao.getNameById(community.getAreaId());
            community.setAreaName(areaName);
            String plateName = this.dictDao.getNameById(community.getPlateId());
            community.setPlateName(plateName);
        }
        //封装数据并返回
        return new PageInfo<>(page,10);
    }
}
