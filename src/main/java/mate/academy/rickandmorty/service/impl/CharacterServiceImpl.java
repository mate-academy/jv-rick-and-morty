package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public CharacterInternalDto getRandomCharacterWiki() {
        long randomId = random.nextLong(characterRepository.count());
        Character character = characterRepository
                .findById(randomId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't get an entity with id: " + randomId));
        return characterMapper.toDto(character);
    }

    @Override
    public void saveAll(List<CharacterExternalResponseDto> characterExternalResponseDtoList) {
        List<Character> characters = characterExternalResponseDtoList.stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }

    @Override
    public List<CharacterInternalDto> getCharactersByName(String name) {
        return characterRepository.findCharactersByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
