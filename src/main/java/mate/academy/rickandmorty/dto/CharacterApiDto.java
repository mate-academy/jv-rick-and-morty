package mate.academy.rickandmorty.dto;

public record CharacterApiDto(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        String image
) {
}
