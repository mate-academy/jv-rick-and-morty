package mate.academy.rickandmorty.dto.external;

public record Info(
        Long count,
        Integer pages,
        String next,
        String prev
) {
}
