package com.rest.service;

import com.rest.dao.KlassStudentDao;
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
    @Autowired
    private KlassStudentDao klassStudentDao;

    public List<Student> findAllStudent(){
        return studentDao.findAllStudent();
    }
    public Student search(String account,String studentName){
        return studentDao.findByAccountOrStudentName(account,studentName);
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
        if (studentDao.updateEmail(id,email)==1&&studentDao.updatePassword(id,password)==1) {
            return studentDao.actival(id);
        }
        else{
            return -1;
        }
    }
    public int delete(Long id){
        return studentDao.delete(id);
    }
    public int add(Student student){
        student.setIsActive(0);
        student.setPassword("123456");
        return studentDao.add(student);
    }
    public int updateInfo(Long id,String account,String email,String studentName){
        return studentDao.updateInfo(id,account,email,studentName);
    }

    public Long queryKlassByStudentIdCourseId(Long studentId,Long courseId){
        System.out.println(klassStudentDao.queryKlassByStudentIdCourseId(studentId,courseId));
        return klassStudentDao.queryKlassByStudentIdCourseId(studentId,courseId);
    }
}
