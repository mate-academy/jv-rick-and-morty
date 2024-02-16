package mate.academy.rickandmorty.dto.external;

public record CharacterResultDto(
        String id,
        String name,
        String status,
        String type,
        String gender,
        String image) {
}
