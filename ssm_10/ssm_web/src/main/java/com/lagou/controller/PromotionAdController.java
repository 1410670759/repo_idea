package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService adService;
    /*
        课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断接收到的上传文件是否为空
        if(file.isEmpty()){
            throw  new RuntimeException();
        }

        //2.获取项目部署路径

        // D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //3.获取原文件名
        //lagou.jpg
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        //12421321.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传

        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        // 如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }

        // 图片就进行了真正的上传
        file.transferTo(filePath);

        // 6. 将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);

        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;

    }

    /**
     * 分页查询广告信息
     * @param adVO
     * @return
     */
    @RequestMapping("/findAllAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO adVO){
        PageInfo allAdByPage = adService.findAllAdByPage(adVO);
        return new ResponseResult(true,200,"响应成功",allAdByPage);
    }



    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        try {
            if(promotionAd.getId() ==null){
                //新增
                promotionAd.setCreateTime(new Date());
                promotionAd.setUpdateTime(new Date());
                adService.savePromotionAd(promotionAd);
                return new ResponseResult(true,200,"响应成功",null);
            }else{
                promotionAd.setUpdateTime(new Date());
                adService.updatePromotionAd(promotionAd);
                return new ResponseResult(true,200,"响应成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 回显广告信息
     *
     */
    @RequestMapping("findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id){
        try {
            PromotionAd promotionAd = adService.findPromotionAdById(id);
            return new ResponseResult(true,200,"响应成功",promotionAd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam int id,int status){
        adService.updatePromotionAdStatus(id,status);

        try {
            //执行修改操作
            if (status ==1){
                adService.updatePromotionAdStatus(id,status);
            }else {
                adService.updatePromotionAdStatus(id,0);
            }
            //保存修改后的状态并返回
            HashMap<String, Integer> map = new HashMap<>();
            map.put("status",status);
            return new ResponseResult(true,200,"响应成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
