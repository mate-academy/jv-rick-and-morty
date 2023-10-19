package mate.academy.rickandmorty.dto.internal;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
