package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class MovieCharacterDataDto {
    private CharacterInfoDataDto info;
    private List<CharacterResultDataDto> results;

}
