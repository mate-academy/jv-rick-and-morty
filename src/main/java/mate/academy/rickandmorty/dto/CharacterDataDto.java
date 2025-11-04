package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterDataDto {
    private List<CharacterApiRequestDto> results;
    private CharacterInfoDto info;
}
