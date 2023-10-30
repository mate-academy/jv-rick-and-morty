package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonageResponseDto;
import mate.academy.rickandmorty.mapper.PersonageMapper;
import mate.academy.rickandmorty.model.Personage;
import mate.academy.rickandmorty.repository.PersonageRepository;
import mate.academy.rickandmorty.service.PersonageService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonageServiceImpl implements PersonageService {
    private final PersonageRepository personageRepository;
    private final PersonageMapper personageMapper;

    @Override
    public List<PersonageResponseDto> getPersonageByNameLike(Pageable pageable, String name) {
        return personageRepository.findAllByNameContains(pageable, name)
                .stream()
                .map(personageMapper::toDto)
                .toList();
    }

    @Override
    public PersonageResponseDto getRandomPersonage() {
        Personage randomPersonage = personageRepository.findRandomPersonage()
                .orElseThrow(() -> new EntityNotFoundException("Can't get random personage"));
        return personageMapper.toDto(randomPersonage);
    }
}
