package mate.academy.rickandmorty.dto.internal;

public record CharacterDto(
        long id,
        int externalId,
        String name,
        String status,
        String gender
) {
}
