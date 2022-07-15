package com.my.stu_course.stu_cour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.my.stu_course.stu_cour.mapper.StuManageMapper")
public class StuCourApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuCourApplication.class, args);
    }

}
