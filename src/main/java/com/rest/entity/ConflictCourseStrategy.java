package com.rest.entity;

public class ConflictCourseStrategy {
    private Long id;
    private Long courseId;

    public ConflictCourseStrategy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
