package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class ApiCharacterDataDto {
    private ApiCharacterInfoDataDto info;
    private List<ApiCharacterResultDataDto> results;

}
