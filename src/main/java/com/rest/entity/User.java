package com.rest.entity;
import com.rest.entity.Teacher;
import com.rest.entity.Student;
public class User {
    private Long id;
    private String account;
    private String password;
    private Integer is_active;
    private String name;
    private String email;
    private String role;
    public User() {
    }
    public User(Teacher teacher){
        id=teacher.getId();
        account=teacher.getAccount();
        password=teacher.getPassword();
        is_active=teacher.isIs_active();
        name=teacher.getTeacher_name();
        email=teacher.getEmail();
        role="teacher";
    }
    public User(Student student){
        id=student.getId();
        account=student.getAccount();
        password=student.getPassword();
        is_active=student.getIs_active();
        name=student.getStudent_name();
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

    public Integer isIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
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

    public String getRoleName() {
        return role;
    }

    public void setRoleName(String roleName) {
        this.role = roleName;
    }
}
