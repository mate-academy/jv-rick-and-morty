package mate.academy.rickandmorty.service.character;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto findRandomCharacter() {
        List<Character> allCharacters = characterRepository.findAll();

        if (allCharacters.isEmpty()) {
            throw new EntityNotFoundException("Table is empty");
        }

        int randomIndex = new Random().nextInt(allCharacters.size());

        return characterMapper.toDto(allCharacters.get(randomIndex));
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name) {
        return characterRepository.findByName("%" + name + "%").stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
