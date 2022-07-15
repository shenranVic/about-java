package com.my.stu_course.stu_cour.controller;

import com.my.stu_course.stu_cour.mapper.CourseManageMapper;
import com.my.stu_course.stu_cour.mapper.StuCourseMapper;
import com.my.stu_course.stu_cour.model.CourseInfo;
import com.my.stu_course.stu_cour.model.ReturnData;
import com.my.stu_course.stu_cour.model.StuInfo;
import com.my.stu_course.stu_cour.service.CourseManageService;
import com.my.stu_course.stu_cour.service.StuManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/CourseManageController")
@Slf4j
public class CourseManageController {

    @Autowired(required = false)
    private CourseManageService courseManageService;
    @Autowired(required = false)
    private CourseManageMapper courseManageMapper;
    @Autowired(required = false)
    private StuCourseMapper stuCourseMapper;

    /**
     * 查询所有课程信息
     * @return
     */
    @GetMapping("/selectAll")
    public ReturnData selectAll()
    {
        List<CourseInfo> list=new ArrayList<>();
        list=courseManageService.selectAll();
        if(list==null)
        {
            log.info("返回为空");
            return new ReturnData<>(400,"未查询到数据");
        }
        else return new ReturnData(200,"查询成功",list);
    }

    /**
     * 根据编号查询课程信息
     * @param id
     * @return
     */
    @GetMapping("selectCourById")
    public ReturnData<CourseInfo> selectCourById(Integer id)
    {
        if(id==null) return new ReturnData<>(400,"课程编号为空");
        else if(courseManageMapper.selectId(id)==null)
            return new ReturnData<CourseInfo>(200,"此编号不存在");
        else if(courseManageService.selectCourById(id)==null)
            return new ReturnData<CourseInfo>(200,"没有数据");
        else return new ReturnData<CourseInfo>(200,"查询成功",courseManageService.selectCourById(id));
    }

    /**
     * 添加一条课程信息
     * @param courseInfo
     * @return
     */
    @PostMapping("courInsert")
    public ReturnData courInsert(@RequestBody CourseInfo courseInfo)
    {
        if(courseInfo==null)
            return new ReturnData(400,"不能插入空数据");
        else if(!(courseManageMapper.selectCourByCourId(courseInfo.getCourId())==null))
            return new ReturnData(400,"不能重复插入");
        else if(courseManageMapper.selectCourId(courseInfo.getCourId())==null
        && !(courseManageMapper.selectCourByName(courseInfo.getCourName())==null))
            return new ReturnData(400,"不能重复插入");
        else {
            Date date =new Date();
            courseInfo.setCreateTime(date);
            courseInfo.setUpdateTime(date);
            courseManageService.courInsert(courseInfo);
            return new ReturnData(200,"插入成功",courseManageMapper.selectCourByCourId(courseInfo.getCourId()));
        }
    }

    /**
     * 根据课程编号更新课程信息
     * @param courseInfo
     * @return
     */
    @PostMapping("/update")
    public ReturnData courUpdate(@RequestBody CourseInfo courseInfo)
    {
        if(courseInfo==null)
            return new ReturnData(400,"不能插入空数据");
        else if(courseManageMapper.selectCourId(courseInfo.getCourId())==null)
            return new ReturnData(400,"更新有误，该课程不存在");
        else {
            Date date =new Date();
            courseInfo.setUpdateTime(date);
            courseManageService.courUpdate(courseInfo);
            if(!(courseManageMapper.selectCourBycourIdUpdate(courseInfo.getCourId()).getCourState().equals("0")))
            {
                stuCourseMapper.logicDeleteByCourId(courseInfo.getCourId());
            }
            return new ReturnData(200,"更新成功",courseManageMapper.selectCourBycourIdUpdate(courseInfo.getCourId()));
        }
    }

    @GetMapping("/delete")
    public ReturnData courDelete(Integer courId)
    {
        if(courId==null)
            return new ReturnData(400,"空数据");
        else if(courseManageMapper.selectCourId(courId)==null)
            return new ReturnData(400,"该课程不存在");
        else
        {
            courseManageService.courDelete(courId);
            stuCourseMapper.updateStateByCourId(courId);
            return new ReturnData(200,"删除成功");
        }
    }

}
