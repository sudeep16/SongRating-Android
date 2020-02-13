package com.user.songratingsystem.model;

public class Songs {
    String SongTitle, Artist, Genre, Duration, Image;

    public Songs(String songTitle, String artist, String genre, String duration, String image) {
        SongTitle = songTitle;
        Artist = artist;
        Genre = genre;
        Duration = duration;
        Image = image;
    }

    public String getSongTitle() {
        return SongTitle;
    }

    public void setSongTitle(String songTitle) {
        SongTitle = songTitle;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
