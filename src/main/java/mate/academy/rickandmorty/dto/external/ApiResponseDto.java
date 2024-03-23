package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ApiResponseDto {
    @JsonProperty("info")
    private ApiInfoDto apiInfoDto;
    private List<ApiCharacterDto> results;
}
