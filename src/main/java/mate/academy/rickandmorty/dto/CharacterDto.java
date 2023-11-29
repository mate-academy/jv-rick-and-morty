package mate.academy.rickandmorty.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CharacterDto {
    private String externalId;
    private String name;
    private String type;
    private String species;
    private String status;
    private String gender;
}
