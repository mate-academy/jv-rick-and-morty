package mate.academy.rickandmorty.dto;

public record ClientCharacterDto(
        long id,
        String name,
        String status,
        String gender
) {
}
