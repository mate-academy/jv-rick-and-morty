package mate.academy.rickandmorty.dto.internal;

public record RickyCharacterDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender
) {
}
