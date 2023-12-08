package mate.academy.rickandmorty.dto.internal;

public record SeriesCharacterResponseDto(
        Long id,
        Long externalId,
        String name,
        String species,
        String status,
        String type
) {
}
