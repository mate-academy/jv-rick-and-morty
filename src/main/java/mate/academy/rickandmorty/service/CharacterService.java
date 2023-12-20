package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private static Long maxId;
    private final CharacterClient characterClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @PostConstruct
    public void init() {
        List<Character> characters = characterClient.getAllCharacters().stream()
                .map(characterMapper::toCharacter)
                .toList();
        List<Character> saved = characterRepository.saveAll(characters);
        maxId = saved.get(saved.size() - 1).getId();
    }

    public CharacterDto getRandomCharacter() {
        Long randomId = random.nextLong(maxId - 1) + 1;
        Optional<Character> character = characterRepository.findById(randomId);
        while (character.isEmpty()) {
            character = characterRepository.findById(randomId);
        }
        return characterMapper.toDto(character.get());
    }

    public List<CharacterDto> getCharactersByName(String name) {
        return characterRepository.getCharactersByNameLikeIgnoreCase("%" + name + "%").stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
