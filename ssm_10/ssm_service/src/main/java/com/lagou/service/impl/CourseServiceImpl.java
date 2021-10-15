package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
//import org.springframework.beans.BeanUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Transactional
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        List<Course> list = courseMapper.findCourseByCondition(courseVo);
        return list;
    }


    /**
     * 保存课程信息
     * @param courseVo
     */
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();
        //相当于一个set方法，将前台传过来的实体类，封住到Course实体中。但是肯定属性会不完全。
        //createTime，updateTime等。
        BeanUtils.copyProperties(course,courseVo);
        //再次进行剩余属性的封装
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        //保存课程
        courseMapper.saveCourse(course);  //将封装过的course对象及逆行保存
        //保存之后，会出现一个courseId，sql逻辑中写好了，直接再次从course中获取

        int id = course.getId();  //这个id是需要和老师表中对应的，取出来给讲师信息用。

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //把其他的属性再次封装
        teacher.setCourseId(id);  //主要是设置讲师的courseId的来源。
        teacher.setCreateTime(new Date());
        teacher.setUpdateTime(new Date());
        teacher.setIsDel(0);
        //保存讲师信息。
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVo findCourseById(int id) {
        CourseVo courseVo = courseMapper.findCourseById(id);
        return courseVo;
    }

    public void updateCourseOrTeacher(CourseVo courseVo){
       //封装Course信息
        Course course = new Course();
        Teacher teacher = new Teacher();
        try {
            BeanUtils.copyProperties(course,courseVo);
            course.setUpdateTime(new Date());
            courseMapper.updateCourse(course);

            //封装讲师信息

            BeanUtils.copyProperties(teacher,courseVo);
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(new Date());
            courseMapper.updateTeacher(teacher);



        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourseStatus(int id, int status) {
        Course course =new Course();
        course.setUpdateTime(new Date());
        course.setId(id);
        course.setStatus(status);

        courseMapper.updateCourseStatus(course);
    }
}
