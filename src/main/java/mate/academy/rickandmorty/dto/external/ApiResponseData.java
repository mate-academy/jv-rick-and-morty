package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class ApiResponseData {
    private InfoDto info;
    private List<CharacterResponseDto> results;
}
