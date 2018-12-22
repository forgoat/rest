package com.rest.dao;

import com.rest.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentDao {
    List<Student> findAllStudent();
    List<Student> findByAccountOrStudent_name(@Param("account")String account,@Param("student_name") String student_name);
    int add(Student student);
    public Student findByAccount(String account);
    public Student findById(Long id);
    public int updatePassword(@Param("id") Long id, @Param("password") String password);
    public int updateEmail(@Param("id")Long id,@Param("email")String email);
    public int actival(@Param("id")Long id,@Param("password")String password,@Param("email")String email);
    public int delete(Long id);
    public int updateInfo(@Param("id")Long id,@Param("account")String account,@Param("email")String email,@Param("student_name")String student_name,@Param("sex")Integer sex);
}
