package mate.academy.rickandmorty.dto.external;

public record InfoPages(
        int count,
        int pages,
        String next,
        String prev) {
}
