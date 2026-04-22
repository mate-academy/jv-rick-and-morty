package mate.academy.rickandmorty.dto.internal;

public record CharacterWikiDto(
        Long id,
        String externalId,
        String name,
        String status,
        String gender
) {}