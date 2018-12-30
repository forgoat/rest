package com.rest.mapper;

import com.rest.po.KlassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KlassStudentMapper {
    public Long findKlass(@Param("courseId") Long courseId,@Param("studentId") Long studentId);
    public List<KlassStudent> findByTeamId(Long teamId);
    public int quitTeam(Long studentId);
    public KlassStudent findByStudentId(Long studentId);
    public int updateTeam(@Param("studentId") Long studentId,@Param("teamId") Long teamId);
    public Long findTeam(@Param("courseId")Long courseId,@Param("studentId")Long studentId);
    Long queryKlassByStudentIdCourseId(@Param("studentId")Long studentId,@Param("courseId")Long courseId);
    public KlassStudent findByCourseIdAndStudentId(@Param("courseId") Long courseId,@Param("studentId") Long studentId);
}
