package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;

@Data
public class CharacterResponseDataDto {
    private CharacterInfoDto info;
    private List<CreateCharacterRequestDto> results;
}
