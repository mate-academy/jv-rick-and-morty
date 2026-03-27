package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class RickAndMortyPageDto {
    private InfoDto info;
    private List<ExternalCharacterDto> results;
}
