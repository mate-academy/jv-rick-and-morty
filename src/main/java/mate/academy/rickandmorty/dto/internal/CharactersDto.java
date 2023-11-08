package mate.academy.rickandmorty.dto.internal;

import lombok.Data;

@Data
public class CharactersDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
