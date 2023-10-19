package mate.academy.rickandmorty.dto.external;

public record RequestInfoDto(
        int count,
        int pages,
        String next,
        String prev
) {
}
