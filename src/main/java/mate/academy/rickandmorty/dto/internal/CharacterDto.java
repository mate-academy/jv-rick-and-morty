package mate.academy.rickandmorty.dto.internal;

import lombok.Data;

@Data
public class CharacterDto {
    private Integer id;
    private Integer externalId;
    private String name;
    private String status;
    private String gender;
}
