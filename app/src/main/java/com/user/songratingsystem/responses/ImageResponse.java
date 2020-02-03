package com.user.songratingsystem.responses;

public class ImageResponse {
    String filename;

    public ImageResponse(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

