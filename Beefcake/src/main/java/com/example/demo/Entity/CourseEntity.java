package com.example.demo.Entity;

public class CourseEntity {
    private Long courseid;
    private String name;
    private String intor;
    private TeacherEntity teacher;
    private int masterCourseid;//主课程id
    private String startTeamTime;
    private String endTeamTime;
    private double presentationWeight;
    private double questionWeight;
    private double reportWeight;
    private int roundNumber;
    private int minMemberNumber;
    private int maxMemberNumber;

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntor(String intor) {
        this.intor = intor;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public void setMasterCourseid(int masterCourseid) {
        this.masterCourseid = masterCourseid;
    }

    public void setStartTeamTime(String startTeamTime) {
        this.startTeamTime = startTeamTime;
    }

    public void setEndTeamTime(String endTeamTime) {
        this.endTeamTime = endTeamTime;
    }

    public void setPresentationWeight(double presentationWeight) {
        this.presentationWeight = presentationWeight;
    }

    public void setQuestionWeight(double questionWeight) {
        this.questionWeight = questionWeight;
    }

    public void setReportWeight(double reportWeight) {
        this.reportWeight = reportWeight;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setMinMemberNumber(int minMemberNumber) {
        this.minMemberNumber = minMemberNumber;
    }

    public void setMaxMemberNumber(int maxMemberNumber) {
        this.maxMemberNumber = maxMemberNumber;
    }

    public Long getCourseid() {
        return courseid;
    }

    public String getName() {
        return name;
    }

    public String getIntor() {
        return intor;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public int getMasterCourseid() {
        return masterCourseid;
    }

    public String getStartTeamTime() {
        return startTeamTime;
    }

    public String getEndTeamTime() {
        return endTeamTime;
    }

    public double getPresentationWeight() {
        return presentationWeight;
    }

    public double getQuestionWeight() {
        return questionWeight;
    }

    public double getReportWeight() {
        return reportWeight;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getMinMemberNumber() {
        return minMemberNumber;
    }

    public int getMaxMemberNumber() {
        return maxMemberNumber;
    }
}
