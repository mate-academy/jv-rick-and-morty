package mate.academy.rickandmorty.dto.request;

public record CharacterRequestDto(
        Long id,
        String name,
        String status,
        String gender
) {
}
