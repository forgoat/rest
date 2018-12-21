package com.rest.dao;

import com.rest.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface TeacherDao {
    public List<Teacher> teacherlist();
    public int add(Teacher teacher);
    public Teacher findByAccount(String account);
    public Teacher findById(Long id);
    public int updatePassword(@Param(value = "id") Long id, @Param(value = "password") String password);
    public int updateEmail(@Param(value = "id")Long id,@Param(value = "email")String email);
    public int actival(@Param("password") String password,@Param("id") Long id);
    public int delete(Long id);
    public List<Teacher> search(@Param("account") String account,@Param("teacher_name") String teacher_name);
    public int updateInfo(@Param("id")Long id, @Param("account") String account, @Param("email") String email, @Param("teacher_name") String teacher_name);
}
