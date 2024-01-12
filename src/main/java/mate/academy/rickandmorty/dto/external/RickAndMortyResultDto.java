package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RickAndMortyResultDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String image;
}
