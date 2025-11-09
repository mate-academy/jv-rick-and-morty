package mate.academy.rickandmorty.dto.internal;

public record SeriesCharacterWikiDto(
        long id,
        long externalId,
        String name,
        String status,
        String gender
) {
}
