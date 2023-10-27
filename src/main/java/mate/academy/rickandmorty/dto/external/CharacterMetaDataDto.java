package mate.academy.rickandmorty.dto.external;

public record CharacterMetaDataDto(
        Integer count,
        Integer pages,
        String next,
        String prev
) {
}
