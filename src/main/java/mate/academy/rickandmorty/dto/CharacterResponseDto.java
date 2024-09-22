package mate.academy.rickandmorty.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterResponseDto {
    private Long id;
    private int externalId;
    private String name;
    private String status;
    private String gender;
}
