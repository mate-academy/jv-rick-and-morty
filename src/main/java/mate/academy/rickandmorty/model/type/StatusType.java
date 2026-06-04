package mate.academy.rickandmorty.model.type;

public enum StatusType {
    ALIVE,
    DEAD,
    UNKNOWN;

    public static StatusType fromString(String status) {
        if (status == null) {
            return StatusType.UNKNOWN;
        }

        try {
            return StatusType.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return StatusType.UNKNOWN;
        }
    }
}
