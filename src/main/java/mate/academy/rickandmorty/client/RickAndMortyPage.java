package mate.academy.rickandmorty.client;

import java.util.List;
import lombok.Data;

public class RickAndMortyPage {
    @Data
    public static class Info {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }

    @Data
    public static class Origin {
        private String name;
    }

    @Data
    public static class Location {
        private String name;
    }

    @Data
    public static class Character {
        private String id;
        private String name;
        private String status;
        private String species;
        private String type;
        private String gender;
        private Origin origin;
        private Location location;
        private String image;
    }

    @Data
    public static class Page {
        private Info info;
        private List<Character> results;
    }
}

