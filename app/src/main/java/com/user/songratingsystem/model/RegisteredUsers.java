package com.user.songratingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class RegisteredUsers {
    String uName, pass, cPass, email, phone, address, gender, imageId;

    public RegisteredUsers(String uName, String pass, String cPass, String email, String phone, String address, String gender, String imageId) {
        this.uName = uName;
        this.pass = pass;
        this.cPass = cPass;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.imageId = imageId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getcPass() {
        return cPass;
    }

    public void setcPass(String cPass) {
        this.cPass = cPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
