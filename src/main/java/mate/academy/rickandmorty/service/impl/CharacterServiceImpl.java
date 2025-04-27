package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.dto.CharactersDto;
import mate.academy.rickandmorty.entity.Character;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterCartoon;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterCartoon characterCartoon;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public CharacterServiceImpl(CharacterCartoon characterCartoon,
                                CharacterRepository characterRepository,
                                CharacterMapper characterMapper) {
        this.characterCartoon = characterCartoon;
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public Character save(CharactersDto dto) {
        Character character = characterMapper.toModel(dto);
        return characterRepository.save(character);
    }

    @Override
    public Page<CharactersDto> searchByName(String name, Pageable pageable) {
        Page<Character> byName = characterRepository.findByNameContainingIgnoreCase(name, pageable);
        return byName.map(characterMapper::toDto);
    }

    @Override
    public CharactersDto getRandomCharacter() {
        return characterMapper.toDto(getRandom());
    }

    @Override
    @PostConstruct
    public void preloadCharacters() {
        if (characterRepository.count() == 0) {
            List<CharactersDto> externalCharacters = characterCartoon.getCharacters();
            for (CharactersDto dto : externalCharacters) {
                save(dto);
            }
        }
    }

    private Character getRandom() {
        List<Character> all = characterRepository.findAll();
        if (all.isEmpty()) {
            throw new IllegalStateException("No characters available to select from.");
        }
        return all.get(new Random().nextInt(all.size()));
    }
}
