package com.rest.dao;

import com.rest.entity.KlassStudent;
import com.rest.entity.TeamStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KlassStudentDao {
    public List<KlassStudent> findKlassStudentByCourseId(Long courseId);
    public Long findKlass(@Param("courseId") Long courseId,@Param("studentId") Long studentId);
    public List<KlassStudent> findByTeamId(Long teamId);
    public int quitTeam(Long studentId);
    public KlassStudent findByStudentId(Long studentId);
    public int updateTeam(@Param("studentId") Long studentId,@Param("teamId") Long teamId);
    public Long findTeam(@Param("courseId")Long courseId,@Param("studentId")Long studentId);
    Long queryKlassByStudentIdCourseId(@Param("studentId")Long studentId,@Param("courseId")Long courseId);
    public KlassStudent findByCourseIdAndStudentId(@Param("courseId") Long courseId,@Param("studentId") Long studentId);
    int insertStudent(@Param("klassId")Long klassId,@Param("studentId")Long studentId,@Param("courseId")Long courseId);
}
