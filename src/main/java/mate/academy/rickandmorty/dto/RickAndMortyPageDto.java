package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RickAndMortyPageDto(
        PageInfoDto info,
        List<RickAndMortyCharacterDto> results) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PageInfoDto(String next) {
    }
}
