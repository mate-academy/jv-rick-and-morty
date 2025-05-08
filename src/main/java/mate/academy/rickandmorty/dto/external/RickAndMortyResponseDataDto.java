package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RickAndMortyResponseDataDto {
    private RickAndMortyInfoDto info;
    private List<RickAndMortyCharacterDto> results;
}
