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

    public List<Student> findAllStudent(){
        return studentDao.findAllStudent();
    }
    public List<Student> search(String account,String student_name){
        return studentDao.findByAccountOrStudent_name(account,student_name);
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
    public int updatePassword(Long id,String password){
        return studentDao.updatePassword(id,password);
    }
    public int updateEmail(Long id,String email){
        return studentDao.updateEmail(id,email);
    }
    public int actival(Long id,String password,String email){
        return studentDao.actival(id,password,email);
    }
    public int delete(Long id){
        return studentDao.delete(id);
    }
    public int add(Student student){
        return studentDao.add(student);
    }
}
