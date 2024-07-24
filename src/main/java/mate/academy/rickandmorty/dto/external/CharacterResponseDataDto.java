package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private MetadataDto info;
    @JsonProperty("results")
    private List<CharacterDataDto> data;
}

