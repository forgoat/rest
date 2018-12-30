package com.rest.entity;

import com.rest.po.Teacher;

import java.util.List;

public class TeacherData {
    private Integer code;
    private String msg;
    private Integer count;
    private List<Teacher> data;

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

    public List<Teacher> getData() {
        return data;
    }

    public void setData(List<Teacher> data) {
        this.data = data;
    }

    public TeacherData(List<Teacher> teacherList)
    {
        code=0;
        msg="";
        count=teacherList.size();
        data=teacherList;
    }
}
