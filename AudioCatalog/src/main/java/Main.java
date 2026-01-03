import models.Audiobook;
import models.MediaObject;
import models.Podcast;
import models.Song;
import services.Manager;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Manager manager = new Manager();

    public static void main(String[] args) {
        boolean running = true;
        manager.loadCatalog();

        while (running) {
            System.out.println("1. Add Media");
            System.out.println("2. Remove Media");
            System.out.println("3. Show All");
            System.out.println("4. Search by Title");
            System.out.println("5. Filter by Genre");
            System.out.println("6. Sort by Title");
            System.out.println("7. Create Playlist");
            System.out.println("8. Add to Playlist");
            System.out.println("9. Save & Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addMediaMenu();
                    break;
                case "2":
                    System.out.print("Enter title to remove: ");
                    manager.removeMedia(scanner.nextLine());
                    break;
                case "3":
                    for (MediaObject m : manager.getAllMedia()) {
                        System.out.println(m);
                    }
                    break;
                case "4":
                    System.out.print("Search query: ");
                    var res = manager.searchByTitle(scanner.nextLine());
                    if(res.isEmpty()) System.out.println("No results.");
                    else res.forEach(System.out::println);
                    break;
                case "5":
                    System.out.print("Enter genre: ");
                    var filtered = manager.filterByGenre(scanner.nextLine());
                    filtered.forEach(System.out::println);
                    break;
                case "6":
                    manager.sortCatalogByTitle();
                    System.out.println("Sorted!");
                    break;
                case "7":
                    System.out.print("Playlist name: ");
                    manager.createPlaylist(scanner.nextLine());
                    break;
                case "8":
                    System.out.print("Playlist name: ");
                    String plName = scanner.nextLine();
                    System.out.print("Media title: ");
                    String medName = scanner.nextLine();
                    manager.addToPlaylist(plName, medName);
                    break;
                case "9":
                    manager.saveCatalog();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void addMediaMenu() {
        System.out.println("Type: 1-Song, 2-Podcast, 3-Audiobook");
        String type = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Duration (sec): ");
        int dur = Integer.parseInt(scanner.nextLine());

        if (type.equals("1")) {
            System.out.print("Album: ");
            String album = scanner.nextLine();
            manager.addMedia(new Song(title, author, genre, year, dur, album));
        } else if (type.equals("2")) {
            System.out.print("Episode #: ");
            int ep = Integer.parseInt(scanner.nextLine());
            manager.addMedia(new Podcast(title, author, genre, year, dur, ep));
        } else if (type.equals("3")) {
            System.out.print("Narrator: ");
            String narr = scanner.nextLine();
            manager.addMedia(new Audiobook(title, author, genre, year, dur, narr));
        } else {
            System.out.println("Wrong type.");
        }
    }
}