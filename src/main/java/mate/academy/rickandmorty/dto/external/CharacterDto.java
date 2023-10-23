package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class CharacterDto {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
}
