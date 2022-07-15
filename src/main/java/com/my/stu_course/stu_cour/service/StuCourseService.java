package com.my.stu_course.stu_cour.service;

import com.my.stu_course.stu_cour.model.StuCourse;

import java.util.List;

public interface StuCourseService {
    List<StuCourse> selectAll();
    StuCourse selectById(Integer id);
    void  insert(StuCourse stuCourse);
    void  update(StuCourse stuCourse);
    void  delete(Integer id);
}
