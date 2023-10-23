package mate.academy.rickandmorty.dto.internal;

import lombok.Data;

@Data
public class CharacterResponseDto {
    private Long id;
    private int externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
}
