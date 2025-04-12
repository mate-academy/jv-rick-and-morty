package mate.academy.rickandmorty.dto.internal;

public record CharacterShortInfoDto(
        Long id,
        String externalId,
        String name,
        String status,
        String gender
) {
}
