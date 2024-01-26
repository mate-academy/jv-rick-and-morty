package mate.academy.rickandmorty.dto.external.episode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EpisodeDto {
    private Integer id;
    private String name;
    @JsonProperty("air_date")
    private String airDate;
    private String episode;
    private String[] characters;
    private String url;
    private String created;
}
