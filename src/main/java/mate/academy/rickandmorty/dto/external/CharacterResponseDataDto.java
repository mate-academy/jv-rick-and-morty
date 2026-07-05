package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private InfoDto info;
    private List<CharacterResponseDto> results;
}
