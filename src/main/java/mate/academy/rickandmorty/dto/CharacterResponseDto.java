package mate.academy.rickandmorty.dto;

import lombok.Data;

@Data
public class CharacterResponseDto {
    private long id;
    private long externalId;
    private String name;
    private String status;
    private String gender;
}
