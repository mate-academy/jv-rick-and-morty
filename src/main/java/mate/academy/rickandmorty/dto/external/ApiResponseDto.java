package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiResponseDto {
    @JsonProperty("info")
    private ApiInfoDto apiInfoDto;
    private ApiCharacterDto[] results;
}
