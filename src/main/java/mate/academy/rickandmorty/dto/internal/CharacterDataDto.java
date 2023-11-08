package mate.academy.rickandmorty.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDataDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
