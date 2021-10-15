package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    /*
        多条件课程列表查询
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVO){
        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);
        System.out.println("list"+list);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return  responseResult;
    }

    /**
     * 图片上传
     */
    //MultipartFile 要与key保持一致
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断接收到的上传文件是否为空？
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径  拿到servletContext对象。 获取路径。
//        String realPath = request.getServletContext().getRealPath("/");
        String realPath = request.getServletContext().getRealPath("/");

        String webappsPath = realPath.substring(0, realPath.indexOf("ssm_web"));
        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();
        //4.生成一个新文件名
        //使用当前时间戳+原始文件的文件后缀名。
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.上传文件
        String uploadPath = webappsPath+"upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录："+filePath);
        }
        //file表示上传的图片，filePath表示新建的一个磁盘内的文件，
        //将上传的文件，写入指定磁盘位置，指定文件名称的文件中，
        file.transferTo(filePath);

        //6.将文件名和文件路径返回，进行相应
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }

    /**
     * 新增课程
     */
    @RequestMapping("/saveOrUpdateCourse")
     public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        System.out.println(courseVo);
        courseService.saveCourseOrTeacher(courseVo);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;

    }

    /**
     * 根据id显示课程信息
     * @param id
     * @return
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int id){
        CourseVo courseVo = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseVo);
        return result;
    }

    @RequestMapping("/updateCourseOrTeacher")
    public ResponseResult updateCourseOrTeacher(@RequestBody CourseVo courseVo){
        courseService.updateCourseOrTeacher(courseVo);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }

    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id,@RequestParam int status){
        courseService.updateCourseStatus(id,status);
        //保存修改后的状态，并返回
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
}
