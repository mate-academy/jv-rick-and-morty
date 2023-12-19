package mate.academy.rickandmorty.dto;

import lombok.Data;

@Data
public class CharacterDto {
    private long id;
    private String name;
    private String status;
    private String gender;
}
