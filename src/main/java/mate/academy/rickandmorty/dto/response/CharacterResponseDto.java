package mate.academy.rickandmorty.dto.response;

public record CharacterResponseDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender
) {
}
