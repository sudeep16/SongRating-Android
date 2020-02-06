package com.user.songratingsystem.responses;

public class RegisterResponse {
    String code,status,messsage;

    public RegisterResponse(String code, String status, String messsage) {
        this.code = code;
        this.status = status;
        this.messsage = messsage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
