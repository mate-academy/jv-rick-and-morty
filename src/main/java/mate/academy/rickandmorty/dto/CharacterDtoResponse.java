package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDtoResponse {
    private List<RickMortyDtoResponse> results;
}
