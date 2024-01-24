package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CharacterResultDto {

    private int id;
    private String name;
    private String status;
    private String gender;

}
