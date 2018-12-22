package com.rest.entity;

import java.math.BigInteger;

public class Klass {
    private Long id;
    private Long course_id;
    private Integer grade;
    private Integer klass_serial;
    private String klass_time;
    private String klass_location;

    public Klass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getKlass_serial() {
        return klass_serial;
    }

    public void setKlass_serial(Integer klass_serial) {
        this.klass_serial = klass_serial;
    }

    public String getKlass_time() {
        return klass_time;
    }

    public void setKlass_time(String klass_time) {
        this.klass_time = klass_time;
    }

    public String getKlass_location() {
        return klass_location;
    }

    public void setKlass_location(String klass_location) {
        this.klass_location = klass_location;
    }
}
