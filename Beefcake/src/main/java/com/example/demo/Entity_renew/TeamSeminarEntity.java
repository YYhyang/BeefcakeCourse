package com.example.demo.Entity_renew;

public class TeamSeminarEntity {
    private Long id;
    private Long klass_seminar_id;
    private Long team_id;
    private int team_order;

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

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public int getTeam_order() {
        return team_order;
    }

    public void setTeam_order(int team_order) {
        this.team_order = team_order;
    }
}
