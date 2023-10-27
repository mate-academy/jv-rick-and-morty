package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private CharacterMetaDataDto info;
    private List<CharacterInfoDto> results;
}
