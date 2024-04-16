package mate.academy.rickandmorty.dto.internal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class InternalCharacterDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
