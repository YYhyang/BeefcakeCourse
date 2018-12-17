package com.example.demo.Entity;

public class StudentEntity {
    private Long studentid;
    private String account;//是否需要待定
    private String name;
    private String email;
    private int sex;

    public void setStudentid(Long studentid) {
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

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public Long getStudentid() {
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
