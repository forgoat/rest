package com.rest.entity;

import com.rest.po.ShareSeminarApplication;
import com.rest.po.ShareTeamApplication;

public class ShareList {
    private Long shareId;
    private Long courseId;
    private String shareCourseName;
    private Long shareTeacherId;
    private String shareTeacherName;
    private Integer courseStatus;//主课程是0，从课程是1
    private Integer shareStatus;//共享分组是0，共享课程是1

    public ShareList() {
    }

    public ShareList(ShareTeamApplication shareTeamApplication){
        shareStatus=0;
        shareId=shareTeamApplication.getId();

    }
    public ShareList(ShareSeminarApplication shareSeminarApplication){
        shareStatus=1;
        shareId=shareSeminarApplication.getId();
    }
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }


    public String getShareCourseName() {
        return shareCourseName;
    }

    public void setShareCourseName(String shareCourseName) {
        this.shareCourseName = shareCourseName;
    }

    public Long getShareTeacherId() {
        return shareTeacherId;
    }

    public void setShareTeacherId(Long shareTeacherId) {
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

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    @Override
    public String toString() {
        return "ShareList{" +
                "shareId=" + shareId +
                ", courseId=" + courseId +
                ", shareCourseName='" + shareCourseName + '\'' +
                ", shareTeacherId=" + shareTeacherId +
                ", shareTeacherName='" + shareTeacherName + '\'' +
                ", courseStatus=" + courseStatus +
                ", shareStatus=" + shareStatus +
                '}';
    }
}
