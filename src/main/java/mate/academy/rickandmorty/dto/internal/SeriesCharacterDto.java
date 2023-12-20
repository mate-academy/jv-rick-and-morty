package mate.academy.rickandmorty.dto.internal;

public record SeriesCharacterDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender
) {}
