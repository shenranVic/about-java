package com.my.stu_course.stu_cour.service.impl;

import com.my.stu_course.stu_cour.mapper.StuCourseMapper;
import com.my.stu_course.stu_cour.model.StuCourse;
import com.my.stu_course.stu_cour.service.StuCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StuCourseServiceImpl implements StuCourseService {
    @Autowired(required = false)
    private StuCourseMapper stuCourseMapper;
    public List<StuCourse> selectAll()
    {
        List list =stuCourseMapper.selectAll();
        if(list==null)
        {
            log.info("服务层返回空");
            return null;
        }
        else return list;
    }
    public StuCourse selectById(Integer id)
    {
        StuCourse stuCourse=stuCourseMapper.selectById(id);
        if(stuCourse==null)
        {
            log.info("服务层返回空");
            return null;
        }
        else return stuCourse;
    }

    public void  insert(StuCourse stuCourse)
    {
        if(stuCourse==null)
            log.info("接收数据为空");
        else stuCourseMapper.insert(stuCourse);
    }
    public void  update(StuCourse stuCourse)
    {
        if(stuCourse==null)
            log.info("接收数据为空");
        else stuCourseMapper.update(stuCourse);
    }
    public void  delete(Integer id)
    {if(id==null)
        log.info("接收数据为空");
        else stuCourseMapper.delete(id);
    }

}
