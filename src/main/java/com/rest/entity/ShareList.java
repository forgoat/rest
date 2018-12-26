package com.rest.entity;

public class ShareList {
    private Integer courseId;
    private Integer shareCourseId;
    private String shareCourseName;
    private Integer shareTeacherId;
    private String shareTeacherName;
    private Integer courseStatus;//主课程是0，从课程是1
    private Integer shareStatus;//共享分组是0，共享课程是1

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getShareCourseId() {
        return shareCourseId;
    }

    public void setShareCourseId(Integer shareCourseId) {
        this.shareCourseId = shareCourseId;
    }

    public String getShareCourseName() {
        return shareCourseName;
    }

    public void setShareCourseName(String shareCourseName) {
        this.shareCourseName = shareCourseName;
    }

    public Integer getShareTeacherId() {
        return shareTeacherId;
    }

    public void setShareTeacherId(Integer shareTeacherId) {
        this.shareTeacherId = shareTeacherId;
    }

    public String getShareTeacherName() {
        return shareTeacherName;
    }

    public void setShareTeacherName(String shareTeacherName) {
        this.shareTeacherName = shareTeacherName;
    }

    public Integer getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Integer getShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(Integer shareStatus) {
        this.shareStatus = shareStatus;
    }
}
