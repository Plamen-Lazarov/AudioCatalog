package models;

public class Podcast extends MediaObject {
    private int episodeNumber;

    public Podcast() {
        super("", "", "", 0, 0);
    }

    public Podcast(String title, String host, String genre, int year, int durationSeconds, int episodeNumber) {
        super(title, host, genre, year, durationSeconds);
        this.episodeNumber = episodeNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String getMediaType() {
        return "Podcast";
    }

    @Override
    public String toString() {
        return super.toString() + " [Ep. #" + episodeNumber + "]";
    }
}