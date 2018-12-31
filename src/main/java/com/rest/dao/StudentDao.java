package com.rest.dao;

import com.rest.entity.Student;
import com.rest.entity.TeamStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface StudentDao {
    List<Student> findAllStudent();
    Student findByAccountOrStudentName(@Param("account")String account,@Param("studentName") String studentName);
    int add(Student student);
    int batchSaveStudent(List<Student> studentList);

    public Student findByAccount(String account);
    public Student findById(Long id);
    public int updatePassword(@Param("id") Long id, @Param("password") String password);
    public int updateEmail(@Param("id")Long id,@Param("email")String email);
    public int actival(Long id);
    public int delete(Long id);
    int deleteAll();
    public int updateInfo(@Param("id")Long id,@Param("account")String account,@Param("email")String email,@Param("studentName")String studentName);
}
