package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class ExternalCharacterDto {
    private ExternalCharacterDtoInfo info;
    private List<ExternalCharacterDtoResult> results;
}
