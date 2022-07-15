package com.my.stu_course.stu_cour.service;

import com.my.stu_course.stu_cour.model.CourseInfo;

import java.util.List;

public interface CourseManageService {
    List<CourseInfo> selectAll();
    CourseInfo selectCourById(Integer id);
    void courInsert(CourseInfo courseInfo);
    void courUpdate(CourseInfo courseInfo);
    void courDelete(Integer id);
}
