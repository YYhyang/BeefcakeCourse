package com.example.demo.Entity_renew;

public class SeminarEntity {
    private Long seminarId;
    private CourseEntity course;
    private ClassEntity klass;
    private String name;
    private String introduction;
    private RoundEntity round;
    private int order;
    private int status;//讨论课状态
    private boolean visible;
    private int teamNumLimit;
    private String signupStartTime;
    private String signupEndTime;

    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public ClassEntity getKlass() {
        return klass;
    }

    public void setKlass(ClassEntity klass) {
        this.klass = klass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public RoundEntity getRound() {
        return round;
    }

    public void setRound(RoundEntity round) {
        this.round = round;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getTeamNumLimit() {
        return teamNumLimit;
    }

    public void setTeamNumLimit(int teamNumLimit) {
        this.teamNumLimit = teamNumLimit;
    }

    public String getSignupStartTime() {
        return signupStartTime;
    }

    public void setSignupStartTime(String signupStartTime) {
        this.signupStartTime = signupStartTime;
    }

    public String getSignupEndTime() {
        return signupEndTime;
    }

    public void setSignupEndTime(String signupEndTime) {
        this.signupEndTime = signupEndTime;
    }
}
