package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickandmortyapiCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void saveAll(List<RickandmortyapiCharacterDto> requestDtos) {
        characterRepository.saveAll(characterMapper.toModelList(requestDtos));
    }

    @Override
    public List<CharacterDto> getAll() {
        return characterMapper.toDtoList(characterRepository.findAll());
    }

    @Override
    public CharacterDto getById(Long id) {
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find character by id " + id));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findAllByName(String name) {
        return characterMapper.toDtoList(characterRepository.findAllByName(name));
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long randomNumber = (long) (Math.random() * characterRepository.count());
        return getById(randomNumber);
    }
}
