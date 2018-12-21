package com.rest.service;

import com.rest.dao.StudentDao;
import com.rest.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> queryAllStudent(){
        return studentDao.queryAllStudent();
    }

    public List<Student> queryStudent(Student student){
        return studentDao.queryStudent(student);
    }

    public Student findByAccount(String account){
        return studentDao.findByAccount(account);
    }
    public Student findById(Long id){
        return studentDao.findById(id);
    }
    public String findPassword(Long id){
        String password=studentDao.findById(id).getPassword();
        return password;
    }
    @Transactional
    public int insertStudent(Student student){
        return studentDao.insertStudent(student);
    }
}
