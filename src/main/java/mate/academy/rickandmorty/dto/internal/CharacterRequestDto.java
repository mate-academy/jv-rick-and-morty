package mate.academy.rickandmorty.dto.internal;

public record CharacterRequestDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender
) {
}
