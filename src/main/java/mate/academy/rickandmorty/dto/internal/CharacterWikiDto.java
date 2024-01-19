package mate.academy.rickandmorty.dto.internal;

import lombok.Builder;

@Builder
public record CharacterWikiDto(
        Integer id,
        Integer externalId,
        String name,
        String status,
        String gender
) {
}
