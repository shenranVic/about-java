package com.my.stu_course.stu_cour.service.impl;

import com.my.stu_course.stu_cour.mapper.CourseManageMapper;
import com.my.stu_course.stu_cour.model.CourseInfo;
import com.my.stu_course.stu_cour.service.CourseManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CourseManageServiceImpl implements CourseManageService{

    @Autowired(required = false)
    private CourseManageMapper courseManageMapper;



    public List<CourseInfo> selectAll()
    {
        List<CourseInfo> list=courseManageMapper.selectAll();
        if(list==null) {log.info("查询为空"); return null;}
        else return list;
    }
    public CourseInfo selectCourById(Integer id)
    {
        return courseManageMapper.selectCourById(id);
    }
    public void courInsert(CourseInfo courseInfo)
    {
        courseManageMapper.courInsert(courseInfo);
    }
    public void courUpdate(CourseInfo courseInfo)
    {
        courseManageMapper.courUpdate(courseInfo);
    }

    public void courDelete(Integer id)
    {
        courseManageMapper.courDelete(id);
    }
}
