package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void saveAll(List<CharacterResponseDto> dtoList) {
        List<Character> characterList = dtoList.stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characterList);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Optional<Character> randomCharacter = characterRepository.findRandomCharacter();
        return randomCharacter.map(characterMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findCharacterByNameContains(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
