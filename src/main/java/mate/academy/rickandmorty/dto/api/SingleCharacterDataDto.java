package mate.academy.rickandmorty.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SingleCharacterDataDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("species")
    private String species;
    @JsonProperty("type")
    private String type;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("origin")
    private LocationDataDto origin;
    @JsonProperty("location")
    private LocationDataDto location;
    @JsonProperty("image")
    private String image;
    @JsonProperty("episode")
    private String[] episode;
    @JsonProperty("url")
    private String url;
    @JsonProperty("created")
    private LocalDateTime created;
}


