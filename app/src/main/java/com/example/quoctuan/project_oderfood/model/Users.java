package com.example.quoctuan.project_oderfood.model;

/**
 * Created by Admin on 12/26/2017.
 */

public class Users {
    private String NAME;
    private String Password;
    private String Phone;

    public Users(String NAME, String password) {
        this.NAME = NAME;
        Password = password;
    }

    public Users() {
    }


    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
