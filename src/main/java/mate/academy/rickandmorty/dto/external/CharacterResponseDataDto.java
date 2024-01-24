package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CharacterResponseDataDto {

    private List<CharacterResultDto> results;

}
