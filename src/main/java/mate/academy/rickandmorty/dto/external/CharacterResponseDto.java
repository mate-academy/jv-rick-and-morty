package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDto {
    @JsonProperty("id")
    private String externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private CharacterOriginAndLocationDto origin;
    private CharacterOriginAndLocationDto location;
    private String image;
    private List<String> episode;
    private String url;
    private LocalDateTime created;
}
