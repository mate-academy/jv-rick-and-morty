package mate.academy.rickandmorty.dto.internal;

import lombok.Data;

@Data
public class CreateCharactersDto {
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
