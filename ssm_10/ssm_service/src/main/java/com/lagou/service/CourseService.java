package com.lagou.service;


import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    public List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 保存课程信息
     * @param courseVo
     */
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 显示课程信息
     */
    public CourseVo findCourseById(int Id);

    /**
     * 修改课程和讲师信息
     * @param courseVo
     */
    public void updateCourseOrTeacher(CourseVo courseVo);

    /**
     * 修改课程状态
     * @param
     */
    public void updateCourseStatus (int id, int status);


}
