package com.user.songratingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class RegisteredUsers {
    String Username, Password, Email, Address, Gender, Phone, Image;

    public RegisteredUsers(String username, String password, String email, String address, String gender, String phone, String image) {
        Username = username;
        Password = password;
        Email = email;
        Address = address;
        Gender = gender;
        Phone = phone;
        Image = image;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}