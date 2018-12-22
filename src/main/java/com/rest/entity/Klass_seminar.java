package com.rest.entity;

import java.sql.Date;

public class Klass_seminar {
    private Long id;
    private Long klass_id;
    private Long seminar_id;
    private Date report_ddl;
    private Integer seminar_status;

    public Klass_seminar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKlass_id() {
        return klass_id;
    }

    public void setKlass_id(Long klass_id) {
        this.klass_id = klass_id;
    }

    public Long getSeminar_id() {
        return seminar_id;
    }

    public void setSeminar_id(Long seminar_id) {
        this.seminar_id = seminar_id;
    }

    public Date getReport_ddl() {
        return report_ddl;
    }

    public void setReport_ddl(Date report_ddl) {
        this.report_ddl = report_ddl;
    }

    public Integer getSeminar_status() {
        return seminar_status;
    }

    public void setSeminar_status(Integer seminar_status) {
        this.seminar_status = seminar_status;
    }
}
