package mate.academy.rickandmorty.dto.external.character;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.rickandmorty.dto.external.RickAndMortyDto;

@Getter
@Setter
public class CharacterResponseDataDto implements RickAndMortyDto {
    private CharacterMetadataDto info;
    private List<CharacterInfoDto> results;
}
