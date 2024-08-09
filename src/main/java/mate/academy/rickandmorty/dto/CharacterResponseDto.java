package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDto {
    private List<CharacterResultsDataDto> results;
}
