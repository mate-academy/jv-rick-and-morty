package mate.academy.rickandmorty.dto.external;

public record ResponseInfoDto(
        Long count, 
        Long pages,
        String next) {
}
