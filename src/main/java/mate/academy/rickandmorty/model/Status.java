package mate.academy.rickandmorty.model;

import java.util.Arrays;

public enum Status {
    ALIVE("Alive"), DEAD("Dead"), UNKNOWN("Unknown");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public static Status getFromString(String string) {
        return Arrays.stream(values())
            .filter(status -> status.name.equalsIgnoreCase(string))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Illegal status"));
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
