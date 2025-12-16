package mate.academy.rickandmorty;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.helper.RandomIdGenerator;
import mate.academy.rickandmorty.dto.CharacterOfRickyAndMortyDto;
import mate.academy.rickandmorty.mappers.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PreparedResponseToController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;
    private final RandomIdGenerator randomIdGenerator;

    public CharacterOfRickyAndMortyDto getCharacterWithRandomId() {
        Long randomId = randomIdGenerator.getRandomIdOfCharacter().longValue();
        Character randomCharacter = characterService.findById(randomId)
                .orElseThrow(() -> new RuntimeException("random character not found"));
        return characterMapper.toDto(randomCharacter);

    }

    public List<CharacterOfRickyAndMortyDto> getCharacterByName(String name) {
        List<Character> allCharactersWithName = characterService.getAllCharactersWithName(name);

        return allCharactersWithName
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
