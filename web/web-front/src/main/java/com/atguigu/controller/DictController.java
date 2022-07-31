package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author mabo
 * @Date 2022/7/29 10:11
 */

@RestController //Ajax
@RequestMapping("/dict")
public class DictController {

    @Reference
    private DictService dictService;

    @RequestMapping("/findListByDictCode/{dictCode}")
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode){

        List<Dict> dictList = this.dictService.findListByDictCode(dictCode);
        return Result.ok(dictList);
    }
    @RequestMapping("/findListByParentId/{parentId}")
    public Result<List<Dict>> findListByParentId(@PathVariable Long parentId){
        List<Dict> dictList = this.dictService.findListByParentId(parentId);
        return Result.ok(dictList);
    }
}

