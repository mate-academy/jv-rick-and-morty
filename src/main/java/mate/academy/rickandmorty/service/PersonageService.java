package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.PersonageResponseDto;
import org.springframework.data.domain.Pageable;

public interface PersonageService {

    List<PersonageResponseDto> getPersonageByNameLike(Pageable pageable, String name);

    PersonageResponseDto getRandomPersonage();
}
