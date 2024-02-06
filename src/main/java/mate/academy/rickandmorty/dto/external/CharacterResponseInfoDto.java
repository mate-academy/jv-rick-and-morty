package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseInfoDto {
    private CharacterInfoDto info;
    private List<CharacterResponseDto> results;
}
