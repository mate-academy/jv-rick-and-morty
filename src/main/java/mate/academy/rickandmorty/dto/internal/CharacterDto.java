package mate.academy.rickandmorty.dto.internal;

public record CharacterDto(
        Long id,
        Integer externalId,
        String name,
        String status,
        String gender
) {
}
