package com.example.demo.Entity;

public class QuestionEntity {
    private Long id;
    private Long klass_seminar_id;
    private Long attendance_id;
    private Long team_id;
    private Long student_id;
    private int is_selected;
    private double score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKlass_seminar_id() {
        return klass_seminar_id;
    }

    public void setKlass_seminar_id(Long klass_seminar_id) {
        this.klass_seminar_id = klass_seminar_id;
    }

    public Long getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(Long attendance_id) {
        this.attendance_id = attendance_id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public int getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(int is_selected) {
        this.is_selected = is_selected;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
