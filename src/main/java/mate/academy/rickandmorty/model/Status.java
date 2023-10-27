package mate.academy.rickandmorty.model;

public enum Status {
    ALIVE("Alive"), DEAD("Dead"), UNKNOWN("Unknown");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public static Status getFromString(String string) {
        return switch (string) {
            case "Alive" -> ALIVE;
            case "Dead" -> DEAD;
            case "unknown" -> UNKNOWN;
            default -> throw new IllegalArgumentException("Illegal status");
        };
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
