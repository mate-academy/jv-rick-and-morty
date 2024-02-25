package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

@Getter
@Setter
public class CharacterResponseDataDto {
    private Long externalId;
    private List<CharacterDto> results;
}
