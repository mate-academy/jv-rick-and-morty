package mate.academy.rickandmorty.dto.external;

public record InfoResponseDto(Long count,
                              Long pages,
                              String next,
                              String prev) {
}
