package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private CharacterMetaDataDto characterMetaDataDto;
    private List<CharacterInfoDto> results;
}
