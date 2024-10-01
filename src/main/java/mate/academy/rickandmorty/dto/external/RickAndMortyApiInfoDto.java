package mate.academy.rickandmorty.dto.external;

import java.math.BigDecimal;

public record RickAndMortyApiInfoDto(
        BigDecimal count,
        BigDecimal pages,
        String next
) {
}
