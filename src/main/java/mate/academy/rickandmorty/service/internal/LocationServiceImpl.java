package mate.academy.rickandmorty.service.internal;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.LocationCreateRequestDto;
import mate.academy.rickandmorty.mapper.LocationMapper;
import mate.academy.rickandmorty.model.Location;
import mate.academy.rickandmorty.repository.LocationRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public Location save(LocationCreateRequestDto locationCreateRequestDto) {
        Location location = locationMapper.toModel(locationCreateRequestDto);

        if (locationRepository.findByName(locationCreateRequestDto.getName()).isPresent()) {
            location.setId(locationRepository.findByName(
                    locationCreateRequestDto.getName()).get().getId());
        }

        return locationRepository.save(location);
    }
}
