package mate.academy.rickandmorty.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SingleCharacterDataDto {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private LocationDataDto origin;
    private LocationDataDto location;
    private String image;
    private String[] episode;
    private String url;
    private LocalDateTime created;
}
