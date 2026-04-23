package mate.academy.rickandmorty.dto.external;

public record CharacterResponseResultsDto(
        Long id,
        String name,
        String status,
        String gender
) {
}
