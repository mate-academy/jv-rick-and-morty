package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class CartoonCharacterInputDataDto {
    @JsonProperty("id")
    private Long externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private LocationInputDataDto origin;
    private LocationInputDataDto location;
    private String image;
    @JsonProperty("episode")
    private List<String> episodes;
    private String url;
    private LocalDateTime created;
}
