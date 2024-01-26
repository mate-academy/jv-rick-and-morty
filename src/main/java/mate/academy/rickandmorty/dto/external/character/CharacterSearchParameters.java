package mate.academy.rickandmorty.dto.external.character;

import lombok.Data;

@Data
public class CharacterSearchParameters {
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
}
