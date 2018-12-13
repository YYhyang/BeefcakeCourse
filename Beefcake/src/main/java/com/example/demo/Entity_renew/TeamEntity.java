package com.example.demo.Entity_renew;

import java.util.List;

public class TeamEntity {
    private int teamId;
    private String name;
    private CourseEntity course;
    private ClassEntity klass;
    private StudentEntity leader;
    private List<StudentEntity> members;
    private boolean valid;

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public void setKlass(ClassEntity klass) {
        this.klass = klass;
    }

    public void setLeader(StudentEntity leader) {
        this.leader = leader;
    }

    public void setMembers(List<StudentEntity> members) {
        this.members = members;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public ClassEntity getKlass() {
        return klass;
    }

    public StudentEntity getLeader() {
        return leader;
    }

    public List<StudentEntity> getMembers() {
        return members;
    }

    public boolean isValid() {
        return valid;
    }
}
