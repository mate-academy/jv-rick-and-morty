package mate.academy.rickandmorty.dto.external;

public record ResponseInfoDto(
        int count,
        int pages,
        String next,
        String prev
) {
}
