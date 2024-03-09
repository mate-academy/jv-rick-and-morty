package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ExternalCharacterResultResponse {
    @JsonProperty("results")
    private List<ExternalCharacterDto> rickAndMortyDataResponseDtoList;
    @JsonProperty("info")
    private ExternalCharacterResulInfoResponse info;
}
