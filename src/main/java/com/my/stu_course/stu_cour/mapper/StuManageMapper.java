package com.my.stu_course.stu_cour.mapper;

import com.my.stu_course.stu_cour.model.Nation;
import com.my.stu_course.stu_cour.model.StuInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StuManageMapper {

    StuInfo selectStuById(Integer stuId);
    Nation selectNationByName(String nation);
    void stuInsert(StuInfo stuInfo);
    void stuUpdate(StuInfo stuInfo);
    List<StuInfo> selectAll();
    void stuDelete(Integer id);
    StuInfo selectStuByStuId(Integer stuId);

}
