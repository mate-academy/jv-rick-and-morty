package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
}
