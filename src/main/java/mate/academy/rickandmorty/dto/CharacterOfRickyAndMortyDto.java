package mate.academy.rickandmorty.dto;

import java.math.BigDecimal;

public record CharacterOfRickyAndMortyDto(
        Long id,
        BigDecimal externalId,
        String name,
        String status,
        String gender
) {

}
