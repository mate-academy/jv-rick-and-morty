package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CreateCharacterDtoList {
    private List<CreateCharacterDto> results;
}
