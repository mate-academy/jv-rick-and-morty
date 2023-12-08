package mate.academy.rickandmorty.dto.external;

public record CharacterResultDto(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender
) {
}
