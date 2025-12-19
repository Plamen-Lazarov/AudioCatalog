package models;

public class Audiobook extends MediaObject {
    private String narrator;

    public Audiobook() {
        super("", "", "", 0, 0);
    }

    public Audiobook(String title, String author, String genre, int year, int durationSeconds, String narrator) {
        super(title, author, genre, year, durationSeconds);
        this.narrator = narrator;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    @Override
    public String getMediaType() {
        return "Audiobook";
    }

    @Override
    public String toString() {
        return super.toString() + " [Narrator: " + narrator + "]";
    }
}