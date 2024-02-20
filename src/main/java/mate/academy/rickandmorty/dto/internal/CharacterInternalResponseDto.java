package mate.academy.rickandmorty.dto.internal;

public record CharacterInternalResponseDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender) {
}
