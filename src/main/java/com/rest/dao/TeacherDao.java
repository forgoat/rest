package com.rest.dao;

import com.rest.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface TeacherDao {
    public List<Teacher> teacherlist();
    public int createTeacher(Teacher teacher);
    public Teacher findByAccount(String account);
}
