package com.rest.dao;

import com.rest.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {

    List<Student> queryAllStudent();

    List<Student> queryStudent(Student student);

    int insertStudent(Student student);

    public Student findByAccount(String account);
    public Student findById(Long id);
}
