package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class ApiResponseDto {
    private List<ApiCharacterDto> results;
    private ApiInfoDto info;
}
