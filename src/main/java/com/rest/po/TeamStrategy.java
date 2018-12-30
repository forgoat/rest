package com.rest.po;

public class TeamStrategy {
    private Long courseId;
    private Integer strategySerial;
    private String strategyName;
    private Long strategyId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getStrategySerial() {
        return strategySerial;
    }

    public void setStrategySerial(Integer strategySerial) {
        this.strategySerial = strategySerial;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
