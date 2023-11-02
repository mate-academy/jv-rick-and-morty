package mate.academy.rickandmorty.dto.internal;

import java.util.List;
import lombok.Data;

@Data
public class PageWIthCharacters {
    private InfoResourcesDto info;
    private List<CharacterDataDto> results;
}
