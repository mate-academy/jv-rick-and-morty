package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExternalCharacterDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
