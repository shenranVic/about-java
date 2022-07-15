package com.my.stu_course.stu_cour.service;

import com.my.stu_course.stu_cour.model.Nation;
import com.my.stu_course.stu_cour.model.StuInfo;

import java.util.List;


public interface StuManageService {

    StuInfo selectStuById(Integer stuId);
    Nation selectNationByName(String nation);
    List<StuInfo> selectAll();
    void stuInsert(StuInfo stuInfo);
    void stuUpdate(StuInfo stuInfo);
    void stuDelete(Integer id);

}
