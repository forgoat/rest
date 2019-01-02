package com.rest.dao;

import com.rest.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamDao {
    List<Team> queryAllTeam(@Param("courseId") Long courseId);//获取所有队伍信息
    List<Student> queryStudentNoTeam(@Param("courseId") Long courseId);//获取未组队学生列表

    /**
     * 创建小组
     */
    int createTeam(@Param("team") Team team); //insert team表
    int addTeammate(@Param("teamId")Long teamId,@Param("studentId")Long studentId);//添加成员 team_student表
    int updateTeamValidApplication(@Param("teamValidApplication") TeamValidApplication teamValidApplication);//小组更新表
    int updateKlassStudent(@Param("klassStudent") KlassStudent klassStudent);//小组更新表

    int updateStatus(@Param("id") Long id);
    public Team findById(Long id);
    public int setValid(@Param("teamId") Long teamId);
    public int updateInfo(@Param("teamId") Long teamId,@Param("teamName") String teamName,@Param("teamSerial") Integer teamSerial);
    public int deleteTeam(Long id);
    public int save(Team team);
    public List<Team> findByCourseId(Long CourseId);
    public int deleteTeamByCourseId(Long courseId);

}