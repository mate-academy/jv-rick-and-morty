package mate.academy.rickandmorty.service.internal;

import mate.academy.rickandmorty.dto.internal.LocationCreateRequestDto;
import mate.academy.rickandmorty.model.Location;

public interface LocationService {
    Location save(LocationCreateRequestDto locationCreateRequestDto);
}
