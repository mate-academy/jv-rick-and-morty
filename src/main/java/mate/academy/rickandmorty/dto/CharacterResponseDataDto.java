package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CharacterResponseDataDto {
    private List<CharacterClientDto> results;
}
