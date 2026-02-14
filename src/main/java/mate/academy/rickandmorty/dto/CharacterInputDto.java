package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterInputDto {
    private CharacterInfoDto info;
    private List<CharacterExternalDto> results;
}
