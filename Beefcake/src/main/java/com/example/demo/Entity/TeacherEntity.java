package com.example.demo.entity;

public class TeacherEntity {
    private Long id;
    private String account;
    private String password;
    private String teacher_name;
    private int is_active;
    private String email;

    public String getTeacher_name() {
        return teacher_name;
    }
    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getIs_active() {
        return is_active;
    }
    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccount() {
        return account;
    }
    public String getEmail() {
        return email;
    }
}
