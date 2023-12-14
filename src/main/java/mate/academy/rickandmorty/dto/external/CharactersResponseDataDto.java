package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties
@Data
public class CharactersResponseDataDto {
    private CharactersInfo info;
    private List<CharacterResponseInfoDto> results;
}
