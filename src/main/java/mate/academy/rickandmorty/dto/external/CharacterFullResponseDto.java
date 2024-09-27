package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterFullResponseDto {
    private CharacterMetadataDto info;
    private List<CharacterResponseDto> results;
}
