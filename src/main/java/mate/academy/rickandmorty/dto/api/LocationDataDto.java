package mate.academy.rickandmorty.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationDataDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
}
