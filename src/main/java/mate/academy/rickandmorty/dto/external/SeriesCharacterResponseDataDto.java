package mate.academy.rickandmorty.dto.external;

import java.util.Set;
import lombok.Data;

@Data
public class SeriesCharacterResponseDataDto {
    private SeriesCharacterMetadataDto info;
    private Set<SeriesCharacterDto> results;
}
