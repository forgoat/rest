package com.rest.service;

import com.rest.dao.CourseDao;
import com.rest.dao.ShareSeminarApplicationDao;
import com.rest.dao.ShareTeamApplicationDao;
import com.rest.dao.TeacherDao;
import com.rest.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ShareSeminarApplicationDao shareSeminarApplicationDao;
    @Autowired
    private ShareTeamApplicationDao shareTeamApplicationDao;
    @Autowired
    private TeacherDao teacherDao;

    public List<Course> queryCourseByStudentId(Long id){
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
    public int acceptTeamShareRequest(Long id){
        return shareTeamApplicationDao.acceptTeamShare(id);
    }

    public int rejectTeamShareRequest(Long id){
        return shareTeamApplicationDao.rejectTeamShare(id);
    }
    public ShareTeamApplication findTeamShareById(Long id){
        return shareTeamApplicationDao.findShareTeamApplication(id);
    }
    public List<ShareTeamApplication> findAllTeamShare(){
        return shareTeamApplicationDao.findAll();
    }
    public int acceptTeamMainCourseId(Long mainCourseId,Long subCourseId){
        return courseDao.acceptMainTeamCourseId(mainCourseId,subCourseId);
    }
    public int acceptSeminarMainCourseId(Long mainCourseId,Long subCourseId){
        return courseDao.acceptMainSeminarId(mainCourseId,subCourseId);
    }

    public List<ShareList> findShareListByCourseId(Long courseId){
        List<ShareSeminarApplication> shareSeminarApplications=shareSeminarApplicationDao.findByCourseId(courseId);
        List<ShareList> shareLists=new ArrayList<ShareList>();
        if (shareSeminarApplications.isEmpty()){
            System.out.println("没有共享讨论课");
        }
        else  {
            System.out.println("查到共享讨论课了");
            for (ShareSeminarApplication shareSeminarApplication : shareSeminarApplications) {
                ShareList shareList = new ShareList(shareSeminarApplication);
                if (shareSeminarApplication.getSubCourseId().equals(courseId)) {
                    //说明本身是从课程
                    shareList.setCourseStatus(1);
                    shareList.setCourseId(shareSeminarApplication.getMainCourseId());
                    Course course = courseDao.findById(shareSeminarApplication.getMainCourseId());
                    Long teacherId=course.getTeacherId();
                    shareList.setShareCourseName(course.getCourseName());
                    shareList.setShareTeacherId(teacherId);
                    Teacher teacher=teacherDao.findById(teacherId);
                    shareList.setShareTeacherName(teacher.getTeacherName());
                } else {
                    //说明本身是主课程
                    shareList.setCourseStatus(0);
                    shareList.setCourseId(shareSeminarApplication.getSubCourseId());
                    Course course=courseDao.findById(shareSeminarApplication.getSubCourseId());
                    shareList.setShareCourseName(course.getCourseName());
                    shareList.setShareTeacherId(shareSeminarApplication.getSubCourseTeacherId());
                    Teacher teacher=teacherDao.findById(shareSeminarApplication.getSubCourseTeacherId());
                    shareList.setShareTeacherName(teacher.getTeacherName());
                }
                shareLists.add(shareList);
            }
        }
        List<ShareTeamApplication> shareTeamApplicationList=findTeamShare(courseId);
        if (shareTeamApplicationList.isEmpty()){
            System.out.println("没有共享分组");
        }
        else {
            System.out.println("查到共享分组了");
            for (ShareTeamApplication shareTeamApplication:shareTeamApplicationList){
                ShareList shareList=new ShareList(shareTeamApplication);
                if(shareTeamApplication.getSubCourseId().equals(courseId)){
                    //从课程
                    shareList.setCourseStatus(1);
                    shareList.setCourseId(shareTeamApplication.getMainCourseId());
                    Course course=courseDao.findById(shareTeamApplication.getMainCourseId());
                    shareList.setShareTeacherId(course.getTeacherId());
                    Teacher teacher=teacherDao.findById(course.getTeacherId());
                    shareList.setShareTeacherName(teacher.getTeacherName());
                }
                else {
                    shareList.setCourseStatus(0);
                    shareList.setCourseId(shareTeamApplication.getSubCourseId());
                    Course course=courseDao.findById(shareTeamApplication.getSubCourseId());
                    shareList.setShareCourseName(course.getCourseName());
                    shareList.setShareTeacherId(shareTeamApplication.getSubCourseTeacherId());
                    Teacher teacher=teacherDao.findById(shareTeamApplication.getSubCourseTeacherId());
                    shareList.setShareTeacherName(teacher.getTeacherName());
                }
                shareLists.add(shareList);
            }
        }
        return shareLists;
    }

    /**
     * 判断是不是从课程
     * @param courseId
     * @return
     */
    public Boolean isSubCourse(Long courseId){
        System.out.println("courseId:"+courseId);
        //System.out.println(courseDao.queryTeamMainCourseIdById(courseId));

        if(courseDao.queryTeamMainCourseIdById(courseId)!=0&&courseDao.queryTeamMainCourseIdById(courseId)!=null){
            System.out.println("courseDao.queryTeamMainCourseIdById(courseId)");
            return true;
        }
        return false;
    }

    public List<ShareTeamApplication> findTeamShareApplicationByMainCourseIdOrSubCourseId(Long courseId){
        return shareTeamApplicationDao.findByCourseId(courseId);
    }

}
