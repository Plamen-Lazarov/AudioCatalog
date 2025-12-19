package models;

public class Song extends MediaObject {
    private String album;

    public Song() {
        super("", "", "", 0, 0);
    }

    public Song(String title, String author, String genre, int year, int durationSeconds, String album) {
        super(title, author, genre, year, durationSeconds);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String getMediaType() {
        return "Song";
    }

    @Override
    public String toString() {
        return super.toString() + " [Album: " + album + "]";
    }
}