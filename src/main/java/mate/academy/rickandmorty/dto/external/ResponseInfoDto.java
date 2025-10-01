package mate.academy.rickandmorty.dto.external;

public record ResponseInfoDto(Integer count,
                              Integer pages,
                              String next,
                              String prev) {
}
