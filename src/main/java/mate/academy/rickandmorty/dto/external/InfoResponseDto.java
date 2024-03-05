package mate.academy.rickandmorty.dto.external;

public record InfoResponseDto(
        int count,
        int pages,
        String next,
        String prev) {
}
