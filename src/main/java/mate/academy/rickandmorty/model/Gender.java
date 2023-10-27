package mate.academy.rickandmorty.model;

public enum Gender {

    MALE, FEMALE, GENDERLESS, UNKNOWN;

    public static Gender getFromString(String string) {
        return switch (string) {
            case "Male" -> MALE;
            case "Female" -> FEMALE;
            case "Genderless" -> GENDERLESS;
            case "unknown" -> UNKNOWN;
            default -> throw new IllegalArgumentException("Illegal gender");
        };
    }
}
