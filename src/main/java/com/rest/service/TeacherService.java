package com.rest.service;

import com.rest.mapper.TeacherMapper;
import com.rest.po.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    public List<Teacher> teacherList() {
        return teacherMapper.teacherlist();
    }

    public int createTeacher(Teacher teacher)
    {
        teacher.setIsActive(0);
        teacher.setPassword("123456");
        return teacherMapper.save(teacher);
    }
    public Teacher findByAccount(String account)
    {
        return teacherMapper.findByAccount(account);
    }
    public Teacher findById(Long id){
        return teacherMapper.findById(id);
    }
    public int updatePassword(Long id,String password){
        return teacherMapper.updatePassword(id,password);
    }
    public int updateEmail(Long id, String email) {
        return teacherMapper.updateEmail(id, email);
    }
    public int actival(Long id,String password)
    {
        if(teacherMapper.updatePassword(id,password)==1&& teacherMapper.actival(id)==1) {
            return 1;
        }
        else {
            return -1;
        }
    }
    public int delete(Long id)
    {
        return teacherMapper.delete(id);
    }
    public List<Teacher> search(String account,String teacherName)
    {
        return teacherMapper.search(account,teacherName);
    }
    public int updateInfo(Long id,String account,String email,String teacherName){
        return teacherMapper.updateInfo(id,account,email,teacherName);
    }
}
