package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CreatePersonageRequestDto;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.PersonageMapper;
import mate.academy.rickandmorty.model.Personage;
import mate.academy.rickandmorty.repository.PersonageRepository;
import mate.academy.rickandmorty.service.PersonageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonageServiceImpl implements PersonageService {
    private final PersonageRepository personageRepository;
    private final PersonageMapper personageMapper;

    @Override
    public List<PersonageDto> findAll() {
        return personageRepository.findAll().stream()
                .map(personageMapper::toDto)
                .toList();
    }

    @Override
    public PersonageDto findById(Long id) {
        return personageRepository.findById(id)
                .map(personageMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't find personage by id: "
                                                               + id));
    }

    @Transactional
    @Override
    public PersonageDto save(CreatePersonageRequestDto requestDto) {
        Personage personage = personageMapper.toModel(requestDto);
        return personageMapper.toDto(personageRepository.save(personage));
    }

    @Transactional
    @Override
    public void savePersonages(List<Personage> personages) {
        personageRepository.saveAll(personages);
    }

    @Transactional
    @Override
    public PersonageDto updateById(Long id, CreatePersonageRequestDto requestDto) {
        Personage personage = personageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find personage by id: "
                                                               + id));
        personageMapper.updatePersonageFromDto(requestDto, personage);
        return personageMapper.toDto(personageRepository.save(personage));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        personageRepository.deleteById(id);
    }
}
