package com.my.stu_course.stu_cour.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class StuInfo {
    private  Integer id;
    private  Integer stuId;       //学号
    private  String stuName;      //姓名
    private  Character stuSex;    //性别
    private  Integer stuAge;      //年龄
    private  String stuEthnic;    //民族
    private  String stuClass;     //所在年级
    private  String stuState;  //状态：0：正常   1：退学   2：逻辑删除
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_time")
    private  Date createTime;     //创建时间
    private  String createBy;     //创建人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_time")
    private  Date updateTime;     //更新时间
    private  String updateBy;     //更新人
}
