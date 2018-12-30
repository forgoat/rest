package com.rest.po;

import java.math.BigInteger;

public class Student {
    private Long id;
    private String account;
    private String password;
    private Integer isActive;
    private String studentName;
    private String email;
    public Student() {
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", studentName='" + studentName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
