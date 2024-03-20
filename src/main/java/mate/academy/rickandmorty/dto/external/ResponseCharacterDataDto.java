package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class ResponseCharacterDataDto {
    private InfoCharacterDto info;
    private List<CreateCharacterDto> results;
}
