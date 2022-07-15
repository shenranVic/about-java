package com.my.stu_course.stu_cour.mapper;

import com.my.stu_course.stu_cour.model.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CourseManageMapper {

    List<CourseInfo> selectAll();
    CourseInfo selectCourById(Integer id);
    void courInsert(CourseInfo courseInfo);
    void courUpdate(CourseInfo courseInfo);
    void courDelete(Integer id);
    CourseInfo selectCourByCourId(Integer courId);
    Integer selectId(Integer id);
    Integer selectCourId(Integer courId);
    String selectCourByName(String courName);
    Integer selectCountName(String courName);
    CourseInfo selectCourBycourIdUpdate(Integer courId);
}
