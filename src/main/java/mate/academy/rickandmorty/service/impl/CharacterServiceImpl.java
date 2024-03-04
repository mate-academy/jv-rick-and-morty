package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exceptions.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.LoadingExternalDataService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        Random random = new Random();
        Long id = random.nextLong(LoadingExternalDataService.getCountCharacters()) + 1;
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find character"));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name, Pageable pageable) {
        return characterRepository.findByNameContainingIgnoreCase(name, pageable)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
