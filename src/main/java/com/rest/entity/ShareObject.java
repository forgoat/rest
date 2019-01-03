package com.rest.entity;

public class ShareObject {
    private Long courseId;
    private String courseName;
    private String teacherName;
    private Long subTeacherId;
    public ShareObject() {
    }

    public ShareObject(Course course){
        courseId=course.getId();
        courseName=course.getCourseName();
        subTeacherId=course.getTeacherId();
    }
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getSubTeacherId() {
        return subTeacherId;
    }

    public void setSubTeacherId(Long subTeacherId) {
        this.subTeacherId = subTeacherId;
    }
}
