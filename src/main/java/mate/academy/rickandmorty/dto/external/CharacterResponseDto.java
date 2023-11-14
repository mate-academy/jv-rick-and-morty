package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResponseDto {
    private InfoDto info;
    private List<CharacterExternalDto> results;

}
