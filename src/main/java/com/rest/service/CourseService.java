package com.rest.service;

import com.rest.mapper.CourseMapper;
import com.rest.mapper.ShareSeminarApplicationMapper;
import com.rest.mapper.ShareTeamApplicationMapper;
import com.rest.mapper.TeacherMapper;
import com.rest.entity.*;
import com.rest.po.Course;
import com.rest.po.ShareSeminarApplication;
import com.rest.po.ShareTeamApplication;
import com.rest.po.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ShareSeminarApplicationMapper shareSeminarApplicationMapper;
    @Autowired
    private ShareTeamApplicationMapper shareTeamApplicationMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    public List<Course> queryCourseByStudentId(Long id){
        return courseMapper.queryCourseByStudentId(id);
    }

    public List<Course> findAllCourse(){
        return courseMapper.findAllCourse();
    }
    public int saveCourse(Course course){
        return courseMapper.saveCourse(course);
    }
    public Course findById(Long id){
        return courseMapper.findById(id);
    }
    public int deleteById(Long id){
        return courseMapper.deleteById(id);
    }
    public int sendSeminarShare(ShareSeminarApplication shareSeminarApplication){
        return shareSeminarApplicationMapper.save(shareSeminarApplication);
    }
    public int sendTeamShare(ShareTeamApplication shareTeamApplication){
        return shareTeamApplicationMapper.save(shareTeamApplication);
    }
    public List<Course> findByTeacherId(Long teacherId){
        return courseMapper.findByTeacherId(teacherId);
    }
    public List<ShareSeminarApplication> findSeminarShare(Long courseId){
        return shareSeminarApplicationMapper.findByMainCourseIdOrSubCourseId(courseId);
    }
    public int acceptSeminarShare(Long shareSeminarId){
        return shareSeminarApplicationMapper.acceptSeminarShare(shareSeminarId);
    }
    public ShareSeminarApplication findByShareSeminarId(Long shareSeminarId){
        return shareSeminarApplicationMapper.findById(shareSeminarId);
    }
    public int rejectSeminarShare(Long shareSeminarId){
        return shareSeminarApplicationMapper.reject(shareSeminarId);
    }
    public List<ShareTeamApplication> findShareTeam(Long courseId){
        return shareTeamApplicationMapper.findBySubCourseId(courseId);
    }
    public List<ShareTeamApplication> findTeamShare(Long courseId){
        return shareTeamApplicationMapper.findByCourseId(courseId);
    }
    public List<ShareSeminarApplication> findAllSeminarShare(Long courseId){
        return shareSeminarApplicationMapper.findByCourseId(courseId);
    }
    public int acceptTeamShareRequest(Long id){
        return shareTeamApplicationMapper.acceptTeamShare(id);
    }

    public int rejectTeamShareRequest(Long id){
        return shareTeamApplicationMapper.rejectTeamShare(id);
    }
    public ShareTeamApplication findTeamShareById(Long id){
        return shareTeamApplicationMapper.findShareTeamApplication(id);
    }
    public List<ShareTeamApplication> findAllTeamShare(){
        return shareTeamApplicationMapper.findAll();
    }
    public int acceptTeamMainCourseId(Long mainCourseId,Long subCourseId){
        return courseMapper.acceptMainTeamCourseId(mainCourseId,subCourseId);
    }
    public int acceptSeminarMainCourseId(Long mainCourseId,Long subCourseId){
        return courseMapper.acceptMainSeminarId(mainCourseId,subCourseId);
    }

    public List<ShareList> findShareListByCourseId(Long courseId){
        List<ShareSeminarApplication> shareSeminarApplications= shareSeminarApplicationMapper.findByCourseId(courseId);
        List<ShareList> shareLists=new ArrayList<ShareList>();
        if(!shareSeminarApplications.isEmpty()) {
            for (ShareSeminarApplication shareSeminarApplication : shareSeminarApplications) {
                ShareList shareList = new ShareList(shareSeminarApplication);
                if (shareSeminarApplication.getSubCourseId().equals(courseId)) {
                    //说明本身是从课程
                    shareList.setCourseStatus(1);
                    shareList.setCourseId(shareSeminarApplication.getMainCourseId());
                    Course course = courseMapper.findById(shareSeminarApplication.getMainCourseId());
                    Long teacherId=course.getTeacherId();
                    shareList.setShareCourseName(course.getCourseName());
                    shareList.setShareTeacherId(teacherId);
                    Teacher teacher= teacherMapper.findById(teacherId);
                    shareList.setShareTeacherName(teacher.getTeacherName());
                } else {
                    //说明本身是主课程
                    shareList.setCourseStatus(0);
                    shareList.setCourseId(shareSeminarApplication.getSubCourseId());
                    Course course= courseMapper.findById(shareSeminarApplication.getSubCourseId());
                    shareList.setShareCourseName(course.getCourseName());
                    shareList.setShareTeacherId(shareSeminarApplication.getSubCourseTeacherId());
                    Teacher teacher= teacherMapper.findById(shareSeminarApplication.getSubCourseTeacherId());
                    shareList.setShareTeacherName(teacher.getTeacherName());
                }
                shareLists.add(shareList);
            }
        }
        List<ShareTeamApplication> shareTeamApplicationList= shareTeamApplicationMapper.findByCourseId(courseId);
        if(!shareTeamApplicationList.isEmpty()){
            for (ShareTeamApplication shareTeamApplication:shareTeamApplicationList){
                ShareList shareList=new ShareList(shareTeamApplication);
                if(shareTeamApplication.getSubCourseId().equals(courseId)){
                    //从课程
                    shareList.setCourseStatus(1);
                    shareList.setCourseId(shareTeamApplication.getMainCourseId());
                    Course course= courseMapper.findById(shareTeamApplication.getMainCourseId());
                    shareList.setShareTeacherId(course.getTeacherId());
                    Teacher teacher= teacherMapper.findById(course.getTeacherId());
                    shareList.setShareTeacherName(teacher.getTeacherName());
                }
                else {
                    shareList.setCourseStatus(0);
                    shareList.setCourseId(shareTeamApplication.getSubCourseId());
                    Course course= courseMapper.findById(shareTeamApplication.getSubCourseId());
                    shareList.setShareCourseName(course.getCourseName());
                    shareList.setShareTeacherId(shareTeamApplication.getSubCourseTeacherId());
                    Teacher teacher= teacherMapper.findById(shareTeamApplication.getSubCourseId());
                    shareList.setShareTeacherName(teacher.getTeacherName());
                }
                shareLists.add(shareList);
            }
        }
        return shareLists;
    }

}
