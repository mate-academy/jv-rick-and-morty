package mate.academy.rickandmorty.dto.external;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private List<CharacterFromApiDto> results = new ArrayList<>();
}
