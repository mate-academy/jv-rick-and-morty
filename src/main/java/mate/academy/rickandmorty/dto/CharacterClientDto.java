package mate.academy.rickandmorty.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterClientDto {
    private long id;
    private String name;
    private String status;
    private String type;
    private String gender;
}
