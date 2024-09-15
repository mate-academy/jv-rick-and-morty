package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CharacterResponseDataDto {
    private Long id;
    private String name;
    private String gender;
    private String status;

}
