package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import org.springframework.data.domain.Pageable;

public interface PersonageService {
    PersonageDto getRandomPersonage();

    List<PersonageDto> searchPersonage(String name, Pageable pageable);
}
