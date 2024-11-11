package mate.academy.rickandmorty.dto.internal;

import lombok.Data;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import java.util.List;

@Data
public class CharacterResponseInfoDto {
    private CharacterInfoDto info;
    private List<CharacterDto> results;
}
