package com.my.stu_course.stu_cour.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;

import java.util.Date;

public class CourseInfo {
    private  Integer id;
    private Integer courId;
    private String courName;
    private String courState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "'create_time'")
    private Date createTime;
    private String createBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;
    private String updateBy;

    public CourseInfo(Integer id,Integer courId, String courName, String courState, Date createTime, String createBy, Date updateTime, String updateBy) {
        this.id=id;
        this.courId = courId;
        this.courName = courName;
        this.courState = courState;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    public CourseInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourId() {
        return courId;
    }

    public void setCourId(Integer courId) {
        this.courId = courId;
    }

    public String getCourName() {
        return courName;
    }

    public void setCourName(String courName) {
        this.courName = courName;
    }

    public String getCourState() {
        return courState;
    }

    public void setCourState(String courState) {
        this.courState = courState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
