package com.my.stu_course.stu_cour.service.impl;

import com.my.stu_course.stu_cour.mapper.StuManageMapper;
import com.my.stu_course.stu_cour.model.Nation;
import com.my.stu_course.stu_cour.model.StuInfo;
import com.my.stu_course.stu_cour.service.StuManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StuManageServiceImpl implements StuManageService {

    @Autowired(required = false)
    private StuManageMapper stuManageMapper;

    public StuInfo selectStuById(Integer stuId)
    {
        return stuManageMapper.selectStuById(stuId);
    }
    public Nation selectNationByName(String nation)
    {
        return stuManageMapper.selectNationByName(nation);
    }

    @Override
    public List<StuInfo> selectAll() {
        return stuManageMapper.selectAll();
    }

    public void stuInsert(@RequestBody StuInfo stuInfo)
    {
        stuManageMapper.stuInsert(stuInfo);
    }
    public void stuUpdate(@RequestBody StuInfo stuInfo)
    {
        stuManageMapper.stuUpdate(stuInfo);
    }

    @Override
    public void stuDelete(Integer id) { stuManageMapper.stuDelete(id); }
}
