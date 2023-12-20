package mate.academy.rickandmorty.dto.external;

public record ApiCharacterResponseDto(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender
) {
}
