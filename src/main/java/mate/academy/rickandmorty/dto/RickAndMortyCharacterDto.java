package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RickAndMortyCharacterDto(
        Long id,
        String name,
        String status,
        String gender) {
}
