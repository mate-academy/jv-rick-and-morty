package mate.academy.rickandmorty.dto;

public record ApiPageInfo(
        int pages,
        int count,
        String next,
        String prev
) {
}
