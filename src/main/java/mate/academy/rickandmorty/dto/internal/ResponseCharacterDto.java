package mate.academy.rickandmorty.dto.internal;

public record ResponseCharacterDto(
        Long id,
        Integer externalId,
        String name,
        String status,
        String gender
) {
}
