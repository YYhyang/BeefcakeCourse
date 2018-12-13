package com.example.demo.Entity_renew;

public class TeacherEntity {
    private int teacherid;
    private String account;//是否需要待定
    private String name;
    private String email;

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
