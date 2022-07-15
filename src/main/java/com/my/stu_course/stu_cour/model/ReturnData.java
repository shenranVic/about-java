package com.my.stu_course.stu_cour.model;

public class ReturnData <T>{
    public  Integer code=0;
    public  String msg="msg";
    public  T data ;



    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ReturnData() {
    }
    public ReturnData(int code,String msg,T data) {
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public ReturnData(String msg,T data) {
        this.msg=msg;
        this.data=data;
    }

    public ReturnData(int code,String msg) {
        this.code=code;
        this.msg=msg;
    }
    public  ReturnData success(String msg, T data)
    {
        ReturnData returnData=new ReturnData(200,msg,data);
        return returnData;
    }


}
