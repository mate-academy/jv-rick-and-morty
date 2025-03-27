package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class RickMortyApiResponseDto {
    @NotNull
    @JsonProperty("info")
    private ResponseInfoDto responseInfo;
    @NotNull
    private List<CharacterApiResponseDto> results;
}
