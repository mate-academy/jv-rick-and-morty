package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RickAndMortyResponseDto {
    private InfoDto info;

    private List<ExternalCharacterDto> results;
}
