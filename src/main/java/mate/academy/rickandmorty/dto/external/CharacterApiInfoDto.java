package mate.academy.rickandmorty.dto.external;

public record CharacterApiInfoDto(
        int count,
        int pages,
        String next,
        String prev
) {}
