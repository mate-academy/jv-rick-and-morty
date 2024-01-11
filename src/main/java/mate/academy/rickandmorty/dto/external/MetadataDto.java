package mate.academy.rickandmorty.dto.external;

public record MetadataDto(
        Integer count,
        Integer pages,
        String next,
        String prev
) {}
