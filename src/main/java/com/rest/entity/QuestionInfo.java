package com.rest.entity;

public class QuestionInfo {
    private Long questionId;
    private Long teamId;
    private Long studentId;
    private String studentName;
    private Integer teamSerial;

    public QuestionInfo() {
    }

    public QuestionInfo(Question question){
        questionId=question.getId();
        teamId=question.getTeamId();
        studentId=question.getStudentId();
    }
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }
}
