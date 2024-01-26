package mate.academy.rickandmorty.dto.external.location;

import lombok.Data;
import mate.academy.rickandmorty.model.Info;

@Data
public class LocationsDto {
    private Info info;
    private LocationDto[] results;
}
