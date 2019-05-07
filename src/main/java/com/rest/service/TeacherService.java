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
        if (teacher!=null) {
            teacher.setIsActive(0);
            teacher.setPassword("123456");
            teacherDao.save(teacher);
            return 1;
        }
        else return 0;
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
    public int actival(Long id,String password)
    {
        if(teacherDao.updatePassword(id,password)==1&&teacherDao.actival(id)==1) {
            return 1;
        }
        else {
            return -1;
        }
    }
    public int delete(Long id)
    {
        return teacherDao.delete(id);
    }
    public List<Teacher> search(String account,String teacherName)
    {
        return teacherDao.search(account,teacherName);
    }
    public int updateInfo(Long id,String account,String email,String teacherName){
        return teacherDao.updateInfo(id,account,email,teacherName);
    }
}
