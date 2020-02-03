package com.user.songratingsystem.responses;

public class LoginResponse {
    String status;
    String usertoken;

    public LoginResponse(String status, String usertoken) {
        this.status = status;
        this.usertoken = usertoken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }
}
