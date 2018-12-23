package com.rest.entity;

import java.math.BigInteger;

public class Teacher {
    private Long id;
    private String account;
    private String password;
    private Integer is_active;
    private String teacher_name;
    private String email;

    public Teacher() {
    }

    public Teacher(Long id,String account,String password,Integer is_active,String teacher_name,String email) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
        this.teacher_name = teacher_name;
        this.is_active=is_active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer isIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
