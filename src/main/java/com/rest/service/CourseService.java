package com.rest.service;

import com.rest.dao.CourseDao;
import com.rest.dao.ShareSeminarApplicationDao;
import com.rest.dao.ShareTeamApplicationDao;
import com.rest.entity.Course;
import com.rest.entity.ShareSeminarApplication;
import com.rest.entity.ShareTeamApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ShareSeminarApplicationDao shareSeminarApplicationDao;
    @Autowired
    private ShareTeamApplicationDao shareTeamApplicationDao;

    public List<Course> queryCourseByStudentId(BigInteger id){
        return courseDao.queryCourseByStudentId(id);
    }

    public List<Course> findAllCourse(){
        return courseDao.findAllCourse();
    }
    public int saveCourse(Course course){
        return courseDao.saveCourse(course);
    }
    public Course findById(Long id){
        return courseDao.findById(id);
    }
    public int deleteById(Long id){
        return courseDao.deleteById(id);
    }
    public int sendSeminarShare(ShareSeminarApplication shareSeminarApplication){
        return shareSeminarApplicationDao.save(shareSeminarApplication);
    }
    public int sendTeamShare(ShareTeamApplication shareTeamApplication){
        return shareTeamApplicationDao.save(shareTeamApplication);
    }
    public List<Course> findByTeacherId(Long teacherId){
        return courseDao.findByTeacherId(teacherId);
    }
    public List<ShareSeminarApplication> findSeminarShare(Long courseId){
        return shareSeminarApplicationDao.findByMainCourseIdOrSubCourseId(courseId);
    }
    public int acceptSeminarShare(Long shareSeminarId){
        return shareSeminarApplicationDao.acceptSeminarShare(shareSeminarId);
    }
    public ShareSeminarApplication findByShareSeminarId(Long shareSeminarId){
        return shareSeminarApplicationDao.findById(shareSeminarId);
    }
    public int rejectSeminarShare(Long shareSeminarId){
        return shareSeminarApplicationDao.reject(shareSeminarId);
    }
    public List<ShareTeamApplication> findShareTeam(Long courseId){
        return shareTeamApplicationDao.findBySubCourseId(courseId);
    }
    public List<ShareTeamApplication> findTeamShare(Long courseId){
        return shareTeamApplicationDao.findByCourseId(courseId);
    }
    public List<ShareSeminarApplication> findAllSeminarShare(Long courseId){
        return shareSeminarApplicationDao.findByCourseId(courseId);
    }
    public int acceptTeamShare(Long shareTeamId){
        return acceptTeamShare(shareTeamId);
    }
    public int rejectTeamShare(Long shareTeamId){
        return rejectTeamShare(shareTeamId);
    }
    public ShareTeamApplication findTeamShareById(Long id){
        return shareTeamApplicationDao.findById(id);
    }
}
