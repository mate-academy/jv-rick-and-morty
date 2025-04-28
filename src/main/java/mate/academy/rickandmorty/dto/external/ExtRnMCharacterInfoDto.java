package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExtRnMCharacterInfoDto {
    @JsonProperty("count")
    private Long recordCount;
    @JsonProperty("pages")
    private Long pagesCount;
    @JsonProperty("next")
    private String nextPageUrl;
    @JsonProperty("prev")
    private String previousPageUrl;

}
