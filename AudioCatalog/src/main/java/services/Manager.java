package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.MediaObject;
import models.Playlist;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Manager {
    private List<MediaObject> catalog;
    private List<Playlist> playlists;
    private ObjectMapper objectMapper;
    private final String FILE_PATH = "catalog_data.json";

    public Manager() {
        this.catalog = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void addMedia(MediaObject media) {
        catalog.add(media);
        System.out.println("Added: " + media.getTitle());
    }

    public void removeMedia(String title) {
        if(catalog.removeIf(m -> m.getTitle().equalsIgnoreCase(title))) {
            System.out.println("Removed: " + title);
        } else {
            System.out.println("Not found: " + title);
        }
    }

    public List<MediaObject> getAllMedia() { return catalog; }

    public List<MediaObject> searchByTitle(String titlePart) {
        return catalog.stream().filter(m -> m.getTitle().toLowerCase().contains(titlePart.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<MediaObject> filterByGenre(String genre) {
        return catalog.stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
    }

    public void sortCatalogByTitle() {
        Collections.sort(catalog);
    }

    public void createPlaylist(String title) {
        playlists.add(new Playlist(title));
        System.out.println("Created playlist: " + title);
    }

    public Playlist getPlaylist(String title) {
        return playlists.stream().filter(p -> p.getTitle().equalsIgnoreCase(title))
                .findFirst().orElse(null);
    }

    public void addToPlaylist(String playlistTitle, String mediaTitle) {
        Playlist playlist = getPlaylist(playlistTitle);
        List<MediaObject> mediaFound = searchByTitle(mediaTitle);

        if (playlist != null && !mediaFound.isEmpty()) {
            playlist.addMedia(mediaFound.get(0));
            System.out.println("Added to " + playlistTitle);
        } else {
            System.out.println("Error: Playlist or media not found.");
        }
    }

    public void saveCatalog() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), catalog);
            System.out.println("Catalog saved to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public void loadCatalog() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return;

            catalog = objectMapper.readValue(file, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, MediaObject.class));
            System.out.println("Loaded " + catalog.size() + " items.");
        } catch (IOException e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }
}