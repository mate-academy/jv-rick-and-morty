package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDataDto {
    private InfoResponseDto info;
    private List<CharacterResponseDto> results;
}
