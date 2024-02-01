package mate.academy.rickandmorty.service.character;

import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.mapper.character.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @Override
    public CharacterDto randomCharacter() {
        Long id = new Random().nextLong(20) + 1;
        return characterMapper.toDto(characterRepository.findById(id).get());
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public void saveAllCharacters() {
        List<Character> characters = characterClient.getCharacters()
                .getResults()
                .stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }
}
