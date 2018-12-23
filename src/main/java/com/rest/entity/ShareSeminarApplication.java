package com.rest.entity;

public class ShareSeminarApplication {
    private Long id;
    private Long main_course_id;
    private Long sub_course_id;
    private Long sub_course_teacher_id;
    private Integer status;

    public ShareSeminarApplication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMain_course_id() {
        return main_course_id;
    }

    public void setMain_course_id(Long main_course_id) {
        this.main_course_id = main_course_id;
    }

    public Long getSub_course_id() {
        return sub_course_id;
    }

    public void setSub_course_id(Long sub_course_id) {
        this.sub_course_id = sub_course_id;
    }

    public Long getSub_course_teacher_id() {
        return sub_course_teacher_id;
    }

    public void setSub_course_teacher_id(Long sub_course_teacher_id) {
        this.sub_course_teacher_id = sub_course_teacher_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
