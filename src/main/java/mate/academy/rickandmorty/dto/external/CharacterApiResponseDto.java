package mate.academy.rickandmorty.dto.external;

public record CharacterApiResponseDto(
        Long id,
        String name,
        String status,
        String gender
) {
}
