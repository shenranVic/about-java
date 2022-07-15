package com.my.stu_course.stu_cour.model;

public class Nation {
    private String id;
    private String nation;

    public Nation() {
    }

    public Nation(String id, String nation) {
        this.id = id;
        this.nation = nation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
