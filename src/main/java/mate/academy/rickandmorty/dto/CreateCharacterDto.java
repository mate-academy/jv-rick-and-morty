package mate.academy.rickandmorty.dto;

import lombok.Data;

@Data
public class CreateCharacterDto {
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
