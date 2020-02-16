package com.example.songratingwearable.model;

public class Songs {
    String SongTitle, Genre;

    public Songs(String songTitle, String genre) {
        SongTitle = songTitle;
        Genre = genre;
    }

    public String getSongTitle() {
        return SongTitle;
    }

    public void setSongTitle(String songTitle) {
        SongTitle = songTitle;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}
