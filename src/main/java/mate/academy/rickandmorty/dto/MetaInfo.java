package mate.academy.rickandmorty.dto;

public record MetaInfo(
        Integer count,
        Integer pages,
        String next,
        String prev) {
}
