package mate.academy.rickandmorty.dto.external;

public record ExternalCharacterInfoDto(
        int count,
        int pages,
        String next,
        String prev
) {
}
