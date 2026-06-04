package mate.academy.rickandmorty.model.type;

public enum GenderType {
    MALE,
    FEMALE,
    GENDERLESS,
    UNKNOWN;

    public static GenderType fromString(String gender) {
        if (gender == null) {
            return GenderType.UNKNOWN;
        }

        try {
            return GenderType.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            return GenderType.UNKNOWN;
        }
    }
}
