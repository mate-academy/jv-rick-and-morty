package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private CharacterInfoDataDto info;
    private List<CharacterDetailsDto> results;
}
