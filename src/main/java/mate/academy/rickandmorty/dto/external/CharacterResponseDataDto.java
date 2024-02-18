package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private List<CharacterExternalResponseDto> results;
    @JsonProperty("pages")
    private int numberOfPages;
}
