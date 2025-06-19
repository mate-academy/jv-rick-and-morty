package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @PostConstruct
    public void saveAllCharacters() {
        List<CharacterDto> characterDtos = characterClient.getCharacters();
        List<Character> characters = characterDtos.stream()
                .map(characterMapper::toModel).toList();
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        if (characters.isEmpty()) {
            throw new RuntimeException("No characters found in database");
        }
        int index = random.nextInt(characters.size());
        Character character = characters.get(index);
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name) {
        List<Character> charactersByName = characterRepository.findByNameContainingIgnoreCase(name);
        return charactersByName.stream()
                .map(characterMapper::toDto).toList();
    }
}
