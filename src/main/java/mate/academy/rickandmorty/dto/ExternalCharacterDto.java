package mate.academy.rickandmorty.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalCharacterDto {
    private Long id;

    private String name;

    private String status;

    private String gender;
}
