package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;


import java.util.List;

public interface CourseMapper {
    /**
     * 多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 保存课程信息
     */
    public int saveCourse(Course course);

    /**
     * 保存讲师信息
     * @param teacher
     * @return
     */
    public int saveTeacher(Teacher teacher);

    /**
     * 通过id查询课程信息
     * @param id
     * @return
     */
    public CourseVo findCourseById(int id);

    /**
     * 修改课程信息
     * @param course
     */
    public void updateCourse(Course course);

    /**
     * 修改讲师信息
     * @param teacher
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     * @param course
     */
    public void updateCourseStatus(Course course);
}
