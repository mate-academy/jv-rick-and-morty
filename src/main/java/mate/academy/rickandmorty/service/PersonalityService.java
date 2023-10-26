package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.PersonalityDto;
import mate.academy.rickandmorty.dto.internal.PersonalitySearchParametersDto;
import org.springframework.data.domain.Pageable;

public interface PersonalityService {
    void saveAllPersonalitiesFromThirdPartAri(List<CharacterDto> characterDtos);

    PersonalityDto getRandomPersonality();

    List<PersonalityDto> getAll(Pageable pageable);

    List<PersonalityDto> search(PersonalitySearchParametersDto params, Pageable pageable);
}
