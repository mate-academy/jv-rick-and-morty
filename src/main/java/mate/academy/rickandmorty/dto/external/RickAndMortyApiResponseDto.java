package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class RickAndMortyApiResponseDto {
    private RickAndMortyMetaInfoDto info;
    private List<RickAndMortyResultDto> results;
}
