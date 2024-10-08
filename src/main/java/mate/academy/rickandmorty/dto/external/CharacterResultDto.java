package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResultDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    @JsonProperty("origin")
    private CharacterOriginDto characterOriginDto;
    @JsonProperty("location")
    private CharacterLocationDto characterLocationDto;
    private String image;
    private List<String> episode;
    private String url;
    private LocalDateTime created;
}
