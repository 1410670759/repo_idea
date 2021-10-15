package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody  ResourseVo resourseVo){
        PageInfo<Resource> allResourceByPage = resourceService.findAllResourceByPage(resourseVo);
        return new ResponseResult(true,200,"响应成功",allResourceByPage);
    }

}
