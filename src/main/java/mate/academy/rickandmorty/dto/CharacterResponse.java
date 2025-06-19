package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponse {
    private CharacterInfoDto info;
    private List<CharacterDto> results;
}
