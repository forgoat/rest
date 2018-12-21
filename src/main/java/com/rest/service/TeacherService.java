package com.rest.service;

import com.rest.dao.TeacherDao;
import com.rest.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    public List<Teacher> teacherList() {
        return teacherDao.teacherlist();
    }

    public int createTeacher(Teacher teacher)
    {
        return teacherDao.createTeacher(teacher);
    }
    public Teacher findByAccount(String account)
    {
        return teacherDao.findByAccount(account);
    }
    public Teacher findById(Long id){
        return teacherDao.findById(id);
    }
    public int updatePassword(Long id,String password){
        return teacherDao.updatePassword(id,password);
    }
    public int updateEmail(Long id, String email) {
        return teacherDao.updateEmail(id, email);
    }
    public int actival(Long id,String password){
        return teacherDao.actival(password,id);
    }
}
