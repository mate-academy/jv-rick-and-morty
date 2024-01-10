package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final ClientService clientService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        List<Character> characters = clientService.getAllCharacters().stream()
                .map(characterMapper::toCharacter)
                .toList();

        characterRepository.saveAll(characters);
    }

    @Override
    public InternalCharacterDto getRandomCharacter() {
        Long randomId = new Random().nextLong(characterRepository.findLastId() + 1);
        Optional<Character> character = characterRepository.findById(randomId);
        while (character.isEmpty()) {
            character = characterRepository.findById(randomId);
        }
        return characterMapper.toDto(character.get());
    }

    @Override
    public List<InternalCharacterDto> getCharactersByName(String name) {
        return characterRepository.getCharactersByNameLikeIgnoreCase("%" + name + "%")
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
