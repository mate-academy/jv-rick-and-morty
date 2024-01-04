package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class RickAndMortyApiResponseDto {
    private InfoResponseDto info;
    private List<CharacterResponseDto> results;
}
