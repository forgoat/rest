package com.rest.service;

import com.rest.mapper.KlassStudentMapper;
import com.rest.mapper.StudentMapper;
import com.rest.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private KlassStudentMapper klassStudentMapper;

    public List<Student> findAllStudent(){
        return studentMapper.findAllStudent();
    }
    public Student search(String account,String studentName){
        return studentMapper.findByAccountOrStudentName(account,studentName);
    }
    public Student findByAccount(String account){
        return studentMapper.findByAccount(account);
    }
    public Student findById(Long id){
        return studentMapper.findById(id);
    }
    public String findPassword(Long id){
        String password= studentMapper.findById(id).getPassword();
        return password;
    }
    public int updatePassword(Long id,String password){
        return studentMapper.updatePassword(id,password);
    }
    public int updateEmail(Long id,String email){
        return studentMapper.updateEmail(id,email);
    }
    public int actival(Long id,String password,String email){
        if (studentMapper.updateEmail(id,email)==1&& studentMapper.updatePassword(id,password)==1) {
            return studentMapper.actival(id);
        }
        else{
            return -1;
        }
    }
    public int delete(Long id){
        return studentMapper.delete(id);
    }
    public int add(Student student){
        student.setIsActive(0);
        student.setPassword("123456");
        return studentMapper.add(student);
    }
    public int updateInfo(Long id,String account,String email,String studentName){
        return studentMapper.updateInfo(id,account,email,studentName);
    }

    public Long queryKlassByStudentIdCourseId(Long studentId,Long courseId){
        System.out.println(klassStudentMapper.queryKlassByStudentIdCourseId(studentId,courseId));
        return klassStudentMapper.queryKlassByStudentIdCourseId(studentId,courseId);
    }
}
