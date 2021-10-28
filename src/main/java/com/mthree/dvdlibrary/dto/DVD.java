package com.mthree.dvdlibrary.dto;

import java.time.LocalDate;
import java.util.Objects;

public class DVD {

    private String title;
    private String releaseDate;
    private String rating;
    private String directorName;
    private String studio;
    private String userNote;

    public DVD(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DVD dvd = (DVD) o;
        return title.equals(dvd.title) && Objects.equals(releaseDate, dvd.releaseDate) && Objects.equals(rating, dvd.rating) && Objects.equals(directorName, dvd.directorName) && Objects.equals(studio, dvd.studio) && Objects.equals(userNote, dvd.userNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, rating, directorName, studio, userNote);
    }

    @Override
    public String toString() {
        return "Title: " + title + '\n' +
                "Release Date: " + releaseDate + '\n' +
                "Rating: " + rating + '\n' +
                "Director Name: " + directorName + '\n' +
                "Studio: " + studio + '\n' +
                "User Note: " + userNote + '\n';
    }
}
