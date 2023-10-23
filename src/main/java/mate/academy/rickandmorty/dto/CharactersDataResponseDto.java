package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CharactersDataResponseDto {

    @JsonProperty("results")
    private List<CharacterDto> characters;

    private InfoDto info;

}
