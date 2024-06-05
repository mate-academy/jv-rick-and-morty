package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.entity.Character;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    public CharacterDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        Character randomCharacter = characters.get(random.nextInt(characters.size()));
        return characterMapper.toDto(randomCharacter);
    }

    public List<CharacterDto> searchCharactersByName(String name, Pageable pageable) {
        List<Character> characters = characterRepository.findByNameContaining(name, pageable);
        return characterMapper.toDtoList(characters);
    }
}
