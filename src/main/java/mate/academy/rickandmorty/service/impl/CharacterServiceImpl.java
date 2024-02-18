package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterInternalResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @Override
    public CharacterInternalResponseDto getRandomCharacter() {
        long randomId = random.nextLong(characterRepository.count());
        Character character = characterRepository.findById(randomId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cannot find a random character by id: "
                                + randomId));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterInternalResponseDto> getCharactersByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public void saveAll(List<CharacterExternalResponseDto> characterExternalResponseDtoList) {
        List<Character> characters = characterExternalResponseDtoList.stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }
}
