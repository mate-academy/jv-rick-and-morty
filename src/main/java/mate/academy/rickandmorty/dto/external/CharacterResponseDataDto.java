package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
