package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    //private OriginDto origin;
    //private LocationResponseDto location;
    //private String image;
    //private List<EpisodeResponseDto> episode;
    //private String url;
    //private LocalDateTime created;
}
