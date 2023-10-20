package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class ListCharacterDto {
    private List<CharacterResponseDto> results;
}
