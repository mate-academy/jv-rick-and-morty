package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class ResponseDataDto {
    private PageInfoDto info;
    private List<CharacterInfoDto> results;
}
