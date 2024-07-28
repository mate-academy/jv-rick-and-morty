package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class InfoResponseDto {
    private InfoPages info;
    private List<CharacterResponseDto> results;
}
