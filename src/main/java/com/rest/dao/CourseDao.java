package com.rest.dao;

import com.rest.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface CourseDao {

    List<Course> queryCourseByStudentId(Long id); //我的课程 所有课程
    List<Course> findAllCourse();
    int saveCourse(Course course);
    Course findById(Long id);
    int deleteById(Long id);
    public int acceptMainSeminarId(@Param("mainCourseId") Long mainCourseId,@Param("subCourseId") Long subCourseId);
    public List<Course> findByTeacherId(Long teacherId);
    public int acceptMainTeamCourseId(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);
}
