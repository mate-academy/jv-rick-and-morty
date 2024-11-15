package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CreatePersonageRequestDto;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.model.Personage;

public interface PersonageService {
    List<PersonageDto> findAll();

    PersonageDto findById(Long id);

    PersonageDto save(CreatePersonageRequestDto requestDto);

    void savePersonages(List<Personage> personages);

    PersonageDto updateById(Long id, CreatePersonageRequestDto requestDto);

    void deleteById(Long id);
}
