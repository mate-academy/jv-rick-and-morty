package mate.academy.rickandmorty.model;

import java.util.Arrays;

public enum Gender {
    MALE("Male"), FEMALE("Female"), GENDERLESS("Genderless"), UNKNOWN("Unknown");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender getFromString(String string) {
        return Arrays.stream(values())
            .filter(status -> status.name.equalsIgnoreCase(string))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Illegal gender"));
    }
}
