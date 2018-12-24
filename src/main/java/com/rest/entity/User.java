package com.rest.entity;
import com.rest.entity.Teacher;
import com.rest.entity.Student;
public class User {
    private Long id;
    private String account;
    private String password;
    private Integer isActive;
    private String name;
    private String email;
    private String role;
    public User() {
    }
    public User(Teacher teacher){
        id=teacher.getId();
        account=teacher.getAccount();
        password=teacher.getPassword();
        isActive=teacher.getIsActive();
        name=teacher.getTeacherName();
        email=teacher.getEmail();
        role="teacher";
    }
    public User(Student student){
        id=student.getId();
        account=student.getAccount();
        password=student.getPassword();
        isActive=student.getIsActive();
        name=student.getStudentName();
        email=student.getEmail();
        role="student";
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
