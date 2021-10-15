package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam int courseId){

        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", sectionList);
        return result;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam int courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
        return result;
    }

    /**
     * 新增或更新章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if(courseSection.getId() == null){
            //新增
            courseContentService.saveSection(courseSection);
            return  new ResponseResult(true,200,"新增章节成功",null);
        }else {
            //修改
            courseContentService.updateSection(courseSection);
            return  new ResponseResult(true,200,"修改章节成功",null);
        }
    }

    /**
     * 修改课程状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id,status);
        //返回状态
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status",status);
        return  new ResponseResult(true,200,"修改课程状态成功",map);

    }

    /**
     * 保存课时信息
     * @param lesson
     * @return
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson lesson){
        try {
            if(lesson.getId() ==null){
                //新增
                courseContentService.saveLesson(lesson);
                return new ResponseResult(true, 200, "响应成功", null);

            }else {
                courseContentService.saveLesson(lesson);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
