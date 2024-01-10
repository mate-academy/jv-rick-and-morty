package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private ResponseInfoDto info;
    private List<CharacterResponseDto> results;
}
