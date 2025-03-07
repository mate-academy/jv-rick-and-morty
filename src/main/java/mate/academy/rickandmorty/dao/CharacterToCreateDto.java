package mate.academy.rickandmorty.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterToCreateDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
