package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharactersClient charactersClient;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void initCharacters() {
        List<Character> list = charactersClient.loadCharacters().stream()
                .map(characterMapper::toCharacter)
                .toList();
        characterRepository.saveAll(list);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        return characterMapper.toDto(
                characters.get(ThreadLocalRandom
                        .current()
                        .nextInt(characters.size())
                )
        );
    }

    @Override
    public List<CharacterDto> searchCharacters(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
