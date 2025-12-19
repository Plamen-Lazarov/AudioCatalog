package models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String title;
    private List<MediaObject> items;

    public Playlist() {
        this.items = new ArrayList<>();
    }

    public Playlist(String title) {
        this.title = title;
        this.items = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MediaObject> getItems() {
        return items;
    }

    public void setItems(List<MediaObject> items) {
        this.items = items;
    }

    public void addMedia(MediaObject media) {
        this.items.add(media);
    }

    public void removeMedia(MediaObject media) {
        this.items.remove(media);
    }

    public int getTotalDuration() {
        int total = 0;
        for (MediaObject item : items) {
            total += item.getDurationSeconds();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Playlist: " + title + " (" + items.size() + " items, Total time: " + getTotalDuration() + "s)";
    }
}