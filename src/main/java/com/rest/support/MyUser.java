package com.rest.support;
import com.rest.entity.Student;
import com.rest.entity.Teacher;

/**
 * @author JuboYu on 2018/12/15.
 * @version 1.0
 */
public class MyUser {
//    private Integer id;
//    private String account;
//    private String password;
//    private String email;
//    private String name;
//    private Boolean activation;
private Long id;
    private String account;
    private String password;
    private Integer is_active;
    private String name;
    private String email;
   // private String role;

    public MyUser(Long id, String account, String password, String email, String name, Integer is_active) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        this.is_active=is_active;
    }

    public MyUser() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }

    public Student toStudent() {
        return new Student(id, account, password, is_active,email, name);
    }

    public Teacher toTeacher() {
        return new Teacher(id, account, password, is_active,email, name);
    }
}
