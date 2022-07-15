package com.my.stu_course.stu_cour.controller;

import com.my.stu_course.stu_cour.mapper.StuCourseMapper;
import com.my.stu_course.stu_cour.mapper.StuManageMapper;
import com.my.stu_course.stu_cour.model.Nation;
import com.my.stu_course.stu_cour.model.ReturnData;
import com.my.stu_course.stu_cour.model.StuInfo;
import com.my.stu_course.stu_cour.service.StuManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/StuManageController")
public class StuManageController {

    @Autowired(required = false)
    private StuManageService stuManageService;
    @Autowired(required = false)
    private StuManageMapper stuManageMapper;
    @Autowired(required = false)
    private StuCourseMapper stuCourseMapper;

    /**
     * 查询所有学生信息
     * @return
     */
    @GetMapping("/selectAll")
    public   ReturnData selectAll()
    {
        List<StuInfo> list=stuManageService.selectAll();
        if(list==null||list.size()==0) return new ReturnData(400,"未查询到数据");
        else return new ReturnData(200,"查询成功",list);
    }


    /**
     * 根据学号查询学生信息
     * @param stuId
     * @return  ReturnData<StuInfo>
     */
    @GetMapping("/selectStuById")
    public  ReturnData<StuInfo> selectStuById(Integer stuId) {
        log.info("根据学号查询学生信息，请求内容[{}]",stuId);
        if(stuId==null)
            return new ReturnData<StuInfo>(400,"学号为空");
        //断言
        StuInfo stuInfo = stuManageService.selectStuById(stuId);
        log.info("响应内容为：[{}]",stuInfo);
        if(stuInfo==null)
        {
            return new ReturnData<StuInfo>(404,"此学生未注册");
        }
        else return new ReturnData<StuInfo>(200,"查询成功",stuInfo);
    }

    /**
     * 根据民族名称，查询民族
     */
    public  Nation selectNationByName(String nation)
    {
        log.info("根据民族名称，查询民族，请求内容[{}]",nation);
        Nation na = stuManageService.selectNationByName(nation);
        log.info("响应内容为：[{}]",nation);
        return na;
    }

    /**
     * 插入一条学生信息记录
     */
    @PostMapping("/insert")
    public ReturnData<StuInfo> stuInsert(@RequestBody StuInfo stuInfo)
    {

        if(stuInfo==null)
        {
            return new ReturnData<StuInfo>(400,"不能插入空值，注册失败");
        }

        //新插入的学生信息，学号不能重复
        else if(stuManageService.selectStuById(stuInfo.getStuId())!=null)
        {
            log.info("插入学号：学号[{}]已存在",stuInfo.getStuId());
            return new ReturnData<StuInfo>(400,"插入学号不合法,该学号已被使用，注册失败");
        }
        //性别必须是”男“,”女“
        else if(stuInfo.getStuSex()!='0'&& stuInfo.getStuSex()!='1')
        {
            log.info("插入性别：插入内容[{}]不合法",stuInfo.getStuSex());
            return new ReturnData<StuInfo>(400,"插入性别不合法，注册失败");
        }
        //年龄[15,100]
        else if(stuInfo.getStuAge().intValue()<15 || stuInfo.getStuAge().intValue()>100)
        {
            log.info("插入年龄：内容[{}]不合法，15-100之间的整数",stuInfo.getStuAge());
            return new ReturnData<StuInfo>(400,"插入年龄不合法，注册失败");
        }
        //民族
        else if(stuManageService.selectNationByName(stuInfo.getStuEthnic())==null)
        {
            log.info("插入民族：内容[{}]不合法",stuInfo.getStuEthnic());
            return new ReturnData<StuInfo>(400,"插入民族不合法，注册失败");
        }
        //状态
        else if( !stuInfo.getStuState().equals("0")&&!stuInfo.getStuState().equals("1")&&!stuInfo.getStuState().equals("2"))
        {
            log.info("插入状态：内容[{}]不合法",stuInfo.getStuState());
            return new ReturnData<StuInfo>(400,"插入状态不合法，注册失败");
        }
        else {
            log.info("插入一条学生信息记录，请求内容为[{}]",stuInfo);
            Date date=new Date();
            System.out.println(date);
            stuInfo.setCreateTime(date);
            stuInfo.setUpdateTime(date);
            stuManageService.stuInsert(stuInfo);
            log.info("插入成功[{}]",stuInfo);
            return new ReturnData(200,"插入成功",stuManageMapper.selectStuByStuId(stuInfo.getStuId()));
        }
    }


    /**
     * 更新一条学生记录，不更新创建时间
     * @param stuInfo
     * @return
     */
    @PostMapping("/update")
    public ReturnData<StuInfo> stuUpdate(@RequestBody @Validated StuInfo stuInfo)
    {
        if(stuInfo==null||stuInfo.getId()==null)
            return new ReturnData<StuInfo>(400,"编号不能为空");
        else if(stuManageMapper.selectStuByStuId(stuInfo.getStuId())==null)
        {
            return new ReturnData<StuInfo>(400,"学号不存在");
        }
        else if(!(stuManageMapper.selectStuById(stuInfo.getStuId()).getId().equals(stuInfo.getId())))
            return new ReturnData<StuInfo>(400,"编号不存在");
        //姓名不为空,
        else if(stuInfo.getStuName()!=null&&(stuInfo.getStuSex()=='0'|| stuInfo.getStuSex()=='1')
                && (stuInfo.getStuAge()>=15 && stuInfo.getStuAge()<=100)
                && stuManageService.selectNationByName(stuInfo.getStuEthnic())!=null
                && (stuInfo.getStuState().equals("0")||stuInfo.getStuState().equals("1")||stuInfo.getStuState().equals("2")))
        {
            Date date=new Date();
            stuInfo.setUpdateTime(date);
            stuManageService.stuUpdate(stuInfo);
            if(!(stuManageMapper.selectStuByStuId(stuInfo.getStuId()).getStuState().equals("0")))
                stuCourseMapper.updateStateByStuId(stuInfo.getStuId());
            return new ReturnData<StuInfo>(200,"更新成功",stuManageMapper.selectStuByStuId(stuInfo.getStuId()));
        }
       else
           return new ReturnData<StuInfo>(400,"更新失败");
    }

    @GetMapping ("/delete")
    public ReturnData stuDelete(Integer stuId)
    {
        if(stuId==null)
            return new ReturnData(400,"编号不能为空");
        else if(stuManageMapper.selectStuById(stuId)==null)
        {
            return new ReturnData(400,"编号不存在");
        }
        else
        {   stuManageService.stuDelete(stuId);

            return new ReturnData(200,"删除成功",stuManageMapper.selectStuByStuId(stuId));
        }
    }


}

