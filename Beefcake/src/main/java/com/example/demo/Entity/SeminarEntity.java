package com.example.demo.Entity;

import java.util.Date;

public class SeminarEntity {
    private Long id;
    private CourseEntity course;
    private RoundEntity round;
    private String seminar_name;
    private String introduction;
    private int max_team;
    private int is_visible;
    private int seminar_serial;
    private Date enroll_start_time;
    private Date enroll_end_time;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSeminar_name() { return seminar_name; }
    public void setSeminar_name(String seminar_name) { this.seminar_name = seminar_name; }
    public int getSeminar_serial() { return seminar_serial; }
    public void setSeminar_serial(int seminar_serial) { this.seminar_serial = seminar_serial; }
    public CourseEntity getCourse() { return course; }
    public void setCourse(CourseEntity course) { this.course = course; }
    public RoundEntity getRound() { return round; }
    public void setRound(RoundEntity round) { this.round = round; }
    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
    public int getMax_team() { return max_team; }
    public void setMax_team(int max_team) { this.max_team = max_team; }
    public int getIs_visible() { return is_visible; }
    public void setIs_visible(int is_visible) { this.is_visible = is_visible; }
    public Date getEnroll_start_time() { return enroll_start_time; }
    public void setEnroll_start_time(Date enroll_start_time) { this.enroll_start_time = enroll_start_time; }
    public Date getEnroll_end_time() { return enroll_end_time; }
    public void setEnroll_end_time(Date enroll_end_time) { this.enroll_end_time = enroll_end_time; }
}
