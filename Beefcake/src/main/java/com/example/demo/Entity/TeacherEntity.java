package com.example.demo.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teacher", schema = "BeefcakeCourse", catalog = "")
public class TeacherEntity implements Serializable {
    private String id;
    private String name;
    private Integer sex;
    private String email;
    private String title;
    private Integer inforInterval;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "infor_interval")
    public Integer getInforInterval() {
        return inforInterval;
    }

    public void setInforInterval(Integer inforInterval) {
        this.inforInterval = inforInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherEntity that = (TeacherEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (inforInterval != null ? !inforInterval.equals(that.inforInterval) : that.inforInterval != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (inforInterval != null ? inforInterval.hashCode() : 0);
        return result;
    }
}
