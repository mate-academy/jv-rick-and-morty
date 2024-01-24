package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @Override
    public void saveAll(List<CharacterResponseDto> responseDtoList) {
        List<Character> characterList = responseDtoList.stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characterList);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long count = characterRepository.count();
        Character randomCharacter = characterRepository.findById(random.nextLong(count))
                .orElseThrow(() -> new RuntimeException("Can't find character"));
        return characterMapper.toDto(randomCharacter);
    }

    @Override
    public List<CharacterDto> searchByName(String name) {
        List<Character> characterList = characterRepository.findAllByNameContains(name);
        return characterList.stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
