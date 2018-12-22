package com.rest.dao;

import com.rest.entity.Course;
import com.rest.entity.Student;
import com.rest.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface TeamDao {
    List<Team> queryAllTeam(@Param("course_id") BigInteger course_id);//获取所有队伍信息

    List<Student> queryStudentNoTeam();//获取未组队学生列表

    int createTeam(@Param("team") Team team); //创建小组

    int updateTeamValidApplication(@Param("team_id") BigInteger team_id,
                                   @Param("teacher_id") BigInteger teacher_id);//小组更新表

    int updateKlassStudent(@Param("team_id") BigInteger team_id,
                           @Param("klass_id") BigInteger klass_id,
                           @Param("student_id") BigInteger student_id,
                           @Param("course_id") BigInteger course_id);//小组更新表
    public Team findById(Long id);
    public int setValid(Long teamId);
    public int updateInfo(@Param("teamId") Long teamId,@Param("team_name") String team_name,@Param("team_serial") Integer team_serial);
    public int deleteTeam(Long id);
}/*
updateKlassStudent(BigInteger team_id,BigInteger klass_id,BigInteger student_id,BigInteger course_id)


*/
