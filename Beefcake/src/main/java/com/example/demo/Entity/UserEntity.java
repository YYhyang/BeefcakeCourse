package com.example.demo.Entity;

public class UserEntity {

    private String account;
    private String password;
    private String role;
    private int isActived;
    private int code;

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIsActived(int isActived) {
        this.isActived = isActived;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getIsActived() {
        return isActived;
    }

    public int getCode() {
        return code;
    }
}
