package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String EXC_MSG_CANT_FIND = "Can't find character with id ";
    private final Random random = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;

    @Override
    public void saveAll(List<Character> characters) {
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterInternalDto getRandomCharacter() {
        long randomId = random.nextLong(characterRepository.count());
        Character character =
                characterRepository.findById(randomId)
                        .orElseThrow(() -> new RuntimeException(EXC_MSG_CANT_FIND + randomId));
        return mapper.toDto(character);
    }
}
