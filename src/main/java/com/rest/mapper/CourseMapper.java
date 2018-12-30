package com.rest.mapper;

import com.rest.po.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> queryCourseByStudentId(Long id); //我的课程 所有课程
    List<Course> findAllCourse();
    int saveCourse(Course course);
    Course findById(Long id);
    int deleteById(Long id);
    public int acceptMainSeminarId(@Param("mainCourseId") Long mainCourseId,@Param("subCourseId") Long subCourseId);
    public List<Course> findByTeacherId(Long teacherId);
    public int acceptMainTeamCourseId(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);
}
