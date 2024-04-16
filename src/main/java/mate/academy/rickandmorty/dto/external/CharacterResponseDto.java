package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CharacterResponseDto {
    private InfoResponseDto info;
    private List<ExternalCharacterDto> result;
}
