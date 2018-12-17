package com.rest.dao;

import com.rest.entity.Course;
import com.rest.entity.Student;
import com.rest.entity.Team;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface TeamDao {
    List<Team> queryAllTeam(BigInteger course_id);//获取所有队伍信息

    List<Student> queryStudentNoTeam();//获取未组队学生列表

    int createTeam(Team team); //创建小组

    int updateTeamValidApplication(BigInteger team_id,
                                   BigInteger teacher_id,
                                   BigInteger status);//小组更新表

    int updateKlassStudent(BigInteger team_id,
                           BigInteger klass_id,
                           BigInteger student_id,
                           BigInteger course_id);//小组更新表


}
