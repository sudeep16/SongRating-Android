package com.user.songratingsystem.model;

public class UserSongs {

        String SongTitle, Artist, Genre, Duration, Rating;

    public UserSongs(String songTitle, String artist, String genre, String duration, String rating) {
        SongTitle = songTitle;
        Artist = artist;
        Genre = genre;
        Duration = duration;
        Rating = rating;
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

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
}
