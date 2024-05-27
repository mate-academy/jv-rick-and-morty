package mate.academy.rickandmorty.dto.external;

public record CharacterMetadataDto(
        Integer count,
        Integer pages,
        String next,
        String prev
) {
}
