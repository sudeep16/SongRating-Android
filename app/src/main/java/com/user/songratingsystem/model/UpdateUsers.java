package com.user.songratingsystem.model;

public class UpdateUsers {

    String Email, Phone, Address;

    public UpdateUsers(String email, String phone, String address) {
        Email = email;
        Phone = phone;
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
