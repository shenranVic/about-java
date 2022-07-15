package com.my.stu_course.stu_cour.mapper;

import com.my.stu_course.stu_cour.model.StuCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StuCourseMapper {
    Integer selectCountStuById(Integer stuId);
    Integer selectCountCourById(Integer courId);
    List<StuCourse> selectAll();
    StuCourse selectById(Integer id);
    void insert(StuCourse stuCourse);
    void update(StuCourse stuCourse);
    void delete(Integer id);

    Integer selectSTuCourId(Integer id);
    StuCourse selectByStuId(Integer stuId);
    StuCourse selectByStuCourId(Integer stuId,Integer courId);
    void logicDeleteByCourId(Integer courId);
    void updateStateByStuId(Integer stuId);
    void updateStateByCourId(Integer courId);

}
