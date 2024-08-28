package mate.academy.rickandmorty.dto.external;

import java.math.BigDecimal;

public record RickandmortyapiInfoDto(
        BigDecimal count,
        BigDecimal pages,
        String next
) {
}
