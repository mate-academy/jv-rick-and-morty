package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResultsDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private ResultsOriginDto origin;
    private ResultsLocationDto location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
}
