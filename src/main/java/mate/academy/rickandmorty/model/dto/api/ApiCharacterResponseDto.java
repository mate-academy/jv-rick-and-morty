package mate.academy.rickandmorty.model.dto.api;

public record ApiCharacterResponseDto(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender
) {
}
