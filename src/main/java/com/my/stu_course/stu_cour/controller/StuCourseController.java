package com.my.stu_course.stu_cour.controller;

import com.my.stu_course.stu_cour.mapper.CourseManageMapper;
import com.my.stu_course.stu_cour.mapper.StuCourseMapper;
import com.my.stu_course.stu_cour.mapper.StuManageMapper;

import com.my.stu_course.stu_cour.model.ReturnData;
import com.my.stu_course.stu_cour.model.StuCourse;

import com.my.stu_course.stu_cour.service.StuCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/StuCourseController")
public class StuCourseController {

    @Autowired(required = false)
    private StuCourseService stuCourseService;
    @Autowired(required = false)
    private StuManageMapper stuManageMapper;
    @Autowired(required = false)
    private CourseManageMapper courseManageMapper;
    @Autowired(required = false)
    private StuCourseMapper stuCourseMapper;


    /**
     * 查看全部选课记录
     * @return
     */
    @GetMapping("selectAll")
    public ReturnData selectAll()
    {
        List<StuCourse> list=new ArrayList<>();
        list=stuCourseService.selectAll();
        if(list==null)
        {
            log.info("返回空");
            return new ReturnData<>(400,"未查询到数据");
        }
        else return new ReturnData(200,"查询成功",list);
    }


    /**
     * 根据编号查询选课记录
     * @param id
     * @return
     */
    @GetMapping("selectById")
    public ReturnData<StuCourse> selectById(Integer id)
    {
        if(id==null||stuCourseMapper.selectSTuCourId(id)==null)
        {
            log.info("选课记录表：查询编号不存在");
            return new ReturnData<>(400,"编号不存在");
        }
        else {
            StuCourse stuCourse=stuCourseService.selectById(id);
            if(stuCourse==null)
            {
                log.info("选课记录查询结果为空");
                return new ReturnData<>(400,"无数据");
            }
            else return new ReturnData<StuCourse>(200,"查询成功",stuCourse);
        }
    }

    /**
     * 插入一条学生选课记录，一个学生最多报三门课，一门课最多被5个学生报
     * 学生存在，课程存在,课程状态锁定:1
     * 不能重复申报
     * @param stuCourse
     * @return
     */
    @PostMapping("insert")
    public ReturnData insert(@RequestBody StuCourse stuCourse)
    {

        if(stuCourse==null||stuCourse.getStuId()==null||stuCourse.getCourId()==null||stuCourse.getStuName()==null
        ||stuCourse.getCourName()==null||stuCourse.getStuAge()==null||stuCourse.getStuSex()==null)
            return new ReturnData(400,"不能插入空数据");
        else if(stuManageMapper.selectStuByStuId(stuCourse.getStuId())==null||courseManageMapper.selectCourByCourId(stuCourse.getCourId())==null)
            return new ReturnData(400,"学生或课程不存在");
        else if(stuCourseMapper.selectCountStuById(stuCourse.getStuId())>=3||stuCourseMapper.selectCountCourById(stuCourse.getCourId())>=5)
            return new ReturnData(400,"已达到最大选课限制或课程名额已满");
        else if(!(stuCourseMapper.selectByStuCourId(stuCourse.getStuId(),stuCourse.getCourId())==null))
            return new ReturnData(400,"你已选过该课程，不能重复选");
        else {
            Date date =new Date();
            stuCourse.setCreateTime(date);
            stuCourse.setUpdateTime(date);
            stuCourseService.insert(stuCourse);
            return new ReturnData(200,"插入成功",stuCourseMapper.selectByStuCourId(stuCourse.getStuId(),stuCourse.getCourId()));
        }
    }


    /**
     * 根据序号更改选课记录
     * @param stuCourse
     * @return
     */
    @PostMapping("/update")
    public ReturnData update(@RequestBody StuCourse stuCourse)
    {
        if(stuCourse==null)
            return new ReturnData(400,"空数据");
        else if(stuCourse.getId()==null||stuCourse.getStuId()==null||stuCourse.getCourId()==null)
            return new ReturnData(400,"该学生或课程不存在");
        else {stuCourseService.delete(stuCourse.getStuId());
        insert(stuCourse);
        Date date =new Date();
        stuCourse.setCreateTime(date);
        stuCourse.setUpdateTime(date);
        return new ReturnData(200,"插入成功",stuCourseMapper.selectByStuCourId(stuCourse.getStuId(),stuCourse.getCourId()));
        }
    }


}
