package mate.academy.rickandmorty.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class CharacterResponseDataDto {
    private CharacterInfoDto info;
    private List<CharacterResultsDto> results;
}
