package mate.academy.rickandmorty.dto.internal;

import lombok.Data;

@Data
public class CharacterRickAndMortyDto {
    private Integer id;
    private Integer externalId;
    private String name;
    private String status;
    private String gender;
}
