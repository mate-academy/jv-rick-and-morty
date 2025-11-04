package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterDataDto {
    private List<CharacterDto> results;
    private CharacterInfoDto info;
}
