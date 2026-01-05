package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class AllCharactersResponseDto {

    private InfoDto info;
    private List<CharacterDto> results;
}
