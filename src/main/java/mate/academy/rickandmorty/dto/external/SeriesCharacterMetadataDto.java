package mate.academy.rickandmorty.dto.external;

public record SeriesCharacterMetadataDto(
        int count,
        int pages,
        String next,
        String prev
) {
}
