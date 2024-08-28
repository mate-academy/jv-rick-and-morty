package mate.academy.rickandmorty.dto.internal;

public record CreateCharacterRequestDto(
        Integer externalId,
        String name,
        String status,
        String gender
) {
}
