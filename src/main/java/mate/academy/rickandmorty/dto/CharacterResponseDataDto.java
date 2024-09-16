package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private CharacterResponseInfoDto info;
    private List<CharacterDto> results;
}
