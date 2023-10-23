package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class ListOfCharacterResponseDto {
    private InformationDto info;
    private List<CharacterResponseDto> results;
}
