package mate.academy.rickandmorty.dto.external;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ResponseCharacterDto {

    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private LocationDto origin;
    private LocationDto location;
    private String image;
    private List<String> episode;
    private String url;
    private OffsetDateTime created;

}
