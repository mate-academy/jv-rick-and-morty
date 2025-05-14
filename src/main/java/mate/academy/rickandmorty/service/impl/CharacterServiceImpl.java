package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.exception.NotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public ExternalCharacterDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        if (characters.isEmpty()) {
            throw new NotFoundException("No characters found in the database.");
        }
        Character randomCharacter = characters.get(random.nextInt(characters.size()));
        return characterMapper.toDto(randomCharacter);
    }

    @Override
    public List<ExternalCharacterDto> searchCharacterByName(String name, Pageable pageable) {
        List<Character> characters = characterRepository.findByNameContaining(name, pageable);
        if (characters.isEmpty()) {
            throw new NotFoundException("No characters found with name: " + name);
        }
        return characterMapper.toDtoList(characters);
    }
}
