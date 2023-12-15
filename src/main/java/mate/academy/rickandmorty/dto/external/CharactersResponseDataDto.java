package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class CharactersResponseDataDto {
    private CharactersInfo info;
    private List<CharacterResponseInfoDto> results;
}
