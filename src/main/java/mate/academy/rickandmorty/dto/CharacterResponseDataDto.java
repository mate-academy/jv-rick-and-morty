package mate.academy.rickandmorty.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.dto.character.CharacterFromApiDto;

@Data
public class CharacterResponseDataDto {
    private List<CharacterFromApiDto> results = new ArrayList<>();
}
