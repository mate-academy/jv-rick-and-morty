package mate.academy.rickandmorty.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class CharacterResponseDataDto {
    private List<CharacterExternalDto> results;
}
