package com.example.demo.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team_seminar", schema = "BeefcakeCourse", catalog = "")
public class TeamSeminarEntity implements Serializable {
    private long id;
    private Long seminarId;
    private Long teamId;
    private Integer no;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "seminar_id")
    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
    }

    @Basic
    @Column(name = "team_id")
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "no")
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamSeminarEntity that = (TeamSeminarEntity) o;

        if (id != that.id) return false;
        if (seminarId != null ? !seminarId.equals(that.seminarId) : that.seminarId != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (no != null ? !no.equals(that.no) : that.no != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (seminarId != null ? seminarId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (no != null ? no.hashCode() : 0);
        return result;
    }
}
