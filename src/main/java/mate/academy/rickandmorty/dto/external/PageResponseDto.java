package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class PageResponseDto {
    private InfoDto info;
    private List<CharacterExternalResponseDto> results;
}
