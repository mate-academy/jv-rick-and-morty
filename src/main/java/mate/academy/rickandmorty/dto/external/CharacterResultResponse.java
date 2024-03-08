package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResultResponse {
    @JsonProperty("results")
    private List<CharacterRickAndMortyDataResponseDto> rickAndMortyDataResponseDtoList;
    @JsonProperty("info")
    private CharacterResulInfoResponse info;
}
