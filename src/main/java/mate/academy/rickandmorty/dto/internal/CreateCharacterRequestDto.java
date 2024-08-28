package mate.academy.rickandmorty.dto.internal;

public record CreateCharacterRequestDto(
        Long externalId,
        String name,
        String status,
        String gender
) {
}
