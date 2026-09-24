package mate.academy.rickandmorty.dto;

import lombok.Data;

@Data
public class CharacterDto {

    private Long id;
    private Long externalId;
    private String status;
    private String name;
    private String gender;
}
