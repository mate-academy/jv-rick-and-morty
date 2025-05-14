package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterApiResponseDto {
    @JsonProperty("results")
    private List<ExternalCharacterDto> results;
    @JsonProperty("info")
    private Info info;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Info {
        @JsonProperty("next")
        private String next;
    }
}
