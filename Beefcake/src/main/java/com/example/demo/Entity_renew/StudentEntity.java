package com.example.demo.Entity_renew;

public class StudentEntity {
    private String studentid;
    private String name;
    private String email;
    private int sex;

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getSex() {
        return sex;
    }
}
