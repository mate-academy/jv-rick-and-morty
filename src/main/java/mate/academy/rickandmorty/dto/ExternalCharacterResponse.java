package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class ExternalCharacterResponse {
    private ExternalInfo info;
    private List<ExternalCharacterDto> results;
}
