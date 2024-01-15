package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private InfoDto info;
    @JsonProperty("results")
    private List<CharacterInfoDto> results;

    public CharacterResponseDataDto(InfoDto info, List<CharacterInfoDto> results) {
        this.info = info;
        this.results = results;
    }
}
