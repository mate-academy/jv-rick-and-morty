package mate.academy.rickandmorty.dto.external;

public record PageInfoDto(
        int count,
        int pages,
        String next) {
}
