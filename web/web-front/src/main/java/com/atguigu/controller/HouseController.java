package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.*;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mabo
 * @Date 2022/7/29 0:45
 */
@RestController
@RequestMapping("/house")
public class HouseController {
    @Reference
    private HouseService houseService;
    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;
    @Reference
    private HouseImageService houseImageService;
    @Reference
    private HouseBrokerService houseBrokerService;

    @PostMapping(value = "/list/{pageNum}/{pageSize}")
    public Result findListPage(@RequestBody HouseQueryVo houseQueryVo,
                               @PathVariable Integer pageNum,
                               @PathVariable Integer pageSize) {
        PageInfo<HouseVo> pageInfo = houseService.findListPage(pageNum, pageSize, houseQueryVo);
        return Result.ok(pageInfo);
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable Long id, HttpServletRequest request) {
        House house = houseService.getById(id);

        Community community = communityService.getById(house.getCommunityId());
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(id);
        List<HouseImage> houseImageList = houseImageService.findList(id, 1);

        Map<String, Object> map = new HashMap<>();
        map.put("house", house);
        map.put("community", community);
        map.put("houseBrokerList", houseBrokerList);
        map.put("houseImage1List", houseImageList);

        map.put("isFollow", false);

        return Result.ok(map);
    }
}
