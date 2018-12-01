package com.example.demo.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "course", schema = "BeefcakeCourse", catalog = "")
public class CourseEntity implements Serializable {
    private long id;
    private String name;
    private String introduction;
    private BigDecimal presentationPercent;
    private BigDecimal questionPercent;
    private BigDecimal reportPercent;
    private String teacherId;
    private Integer isMain;
    private Integer roundNo;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "presentation_percent")
    public BigDecimal getPresentationPercent() {
        return presentationPercent;
    }

    public void setPresentationPercent(BigDecimal presentationPercent) {
        this.presentationPercent = presentationPercent;
    }

    @Basic
    @Column(name = "question_percent")
    public BigDecimal getQuestionPercent() {
        return questionPercent;
    }

    public void setQuestionPercent(BigDecimal questionPercent) {
        this.questionPercent = questionPercent;
    }

    @Basic
    @Column(name = "report_percent")
    public BigDecimal getReportPercent() {
        return reportPercent;
    }

    public void setReportPercent(BigDecimal reportPercent) {
        this.reportPercent = reportPercent;
    }

    @Basic
    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "is_main")
    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    @Basic
    @Column(name = "round_no")
    public Integer getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(Integer roundNo) {
        this.roundNo = roundNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (presentationPercent != null ? !presentationPercent.equals(that.presentationPercent) : that.presentationPercent != null)
            return false;
        if (questionPercent != null ? !questionPercent.equals(that.questionPercent) : that.questionPercent != null)
            return false;
        if (reportPercent != null ? !reportPercent.equals(that.reportPercent) : that.reportPercent != null)
            return false;
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        if (isMain != null ? !isMain.equals(that.isMain) : that.isMain != null) return false;
        if (roundNo != null ? !roundNo.equals(that.roundNo) : that.roundNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (presentationPercent != null ? presentationPercent.hashCode() : 0);
        result = 31 * result + (questionPercent != null ? questionPercent.hashCode() : 0);
        result = 31 * result + (reportPercent != null ? reportPercent.hashCode() : 0);
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (isMain != null ? isMain.hashCode() : 0);
        result = 31 * result + (roundNo != null ? roundNo.hashCode() : 0);
        return result;
    }
}
