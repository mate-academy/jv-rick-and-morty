package mate.academy.rickandmorty.dto.internal;

import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.dto.InfoResourcesDto;

@Data
public class PageWIthInfoAdnCharacters {
    private InfoResourcesDto info;
    private List<CharacterDataDto> results;
}
