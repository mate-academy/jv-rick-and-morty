package mate.academy.rickandmorty.dto.external;

public record CharacterMetadataDto(
        Long count,
        Long pages,
        String next,
        String prev) {
}
