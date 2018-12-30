package com.rest.entity;

import com.rest.po.Student;

import java.util.List;

public class StudentData {
    private Integer code;
    private String msg;
    private Integer count;
    private List<Student> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    public StudentData(List<Student> studentList)
    {
        code=0;
        msg="";
        count=studentList.size();
        data=studentList;
    }
}
