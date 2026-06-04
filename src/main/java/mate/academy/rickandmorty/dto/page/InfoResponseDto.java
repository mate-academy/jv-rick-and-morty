package mate.academy.rickandmorty.dto.page;

public record InfoResponseDto(
        Long count,
        int pages,
        String next,
        String prev) {
}
