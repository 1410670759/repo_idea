package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {


    @Autowired
    private CourseContentMapper courseContentMapper;
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {

        List<CourseSection> sectionList = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return sectionList;

    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /**
     * 新增章节
     * @param courseSection
     */
    @Override
    public void saveSection(CourseSection courseSection) {
        courseSection.setCreateTime(new Date());
        courseSection.setUpdateTime(new Date());

        courseContentMapper.saveSection(courseSection);
    }

    /**
     * 修改章节信息
     * @param courseSection
     */
    @Override
    public void updateSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSection(courseSection);
    }

    /**
     * 修改章节状态
     * @param
     */
    @Override
    public void updateSectionStatus(int id,int status) {
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(section);

    }

    @Override
    public void saveLesson(CourseLesson lesson) {
        lesson.setCreateTime(new Date());
        lesson.setUpdateTime(new Date());
        courseContentMapper.saveLesson(lesson);
    }

    /**
     * 保存课时信息
     */

}
