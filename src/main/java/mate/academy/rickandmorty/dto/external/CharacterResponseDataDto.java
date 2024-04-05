package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CharacterResponseDataDto {
    private CharacterResponseMetaDataDto info;
    private List<CharacterResponseDto> results;
}
