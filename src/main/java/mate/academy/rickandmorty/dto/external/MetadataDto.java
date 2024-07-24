package mate.academy.rickandmorty.dto.external;

public record MetadataDto(
        int count,
        int pages,
        String next,
        String prev) {
}
