package com.example.demo.Entity_renew;

public class TeamSeminarEntity {
    private Long id;
    private TeamEntity team;
    private SeminarEntity seminar;
    private int no;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public SeminarEntity getSeminar() {
        return seminar;
    }

    public void setSeminar(SeminarEntity seminar) {
        this.seminar = seminar;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
