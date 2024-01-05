package mate.academy.rickandmorty.dto.external;

public record CharacterExternalDto(
        Long id,
        String name,
        String status,
        String gender) {
}
