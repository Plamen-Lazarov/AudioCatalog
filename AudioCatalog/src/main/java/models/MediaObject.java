package models;

import java.util.Objects;

public abstract class MediaObject implements Comparable<MediaObject> {
    private String title;
    private String author;
    private String genre;
    private int year;
    private int durationSeconds;

    public MediaObject() {
    }

    public MediaObject(String title, String author, String genre, int year, int durationSeconds) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.durationSeconds = durationSeconds;
    }
    public abstract String getMediaType();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getFormattedDuration() {
        return String.format("%02d:%02d", durationSeconds / 60, durationSeconds % 60);
    }
    @Override
    public int compareTo(MediaObject other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        MediaObject that = (MediaObject) other;
        return year == that.year &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%d) | %s",
                getMediaType(), author, title, year, getFormattedDuration());
    }
}