package mate.academy.rickandmorty;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.Client;
import mate.academy.rickandmorty.dto.CharacterOfRickyAndMortyDto;
import mate.academy.rickandmorty.dto.RandMResponseDto;
import mate.academy.rickandmorty.mappers.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PreparedResponseToController {

    private final List<String> names;
    private final Client client;
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterOfRickyAndMortyDto getCharacterWithRandomId() {
        RandMResponseDto rickyAndMorty = client.getRickyAndMortyRandomCharacter();
        return characterService.save(rickyAndMorty);

    }

    public List<CharacterOfRickyAndMortyDto> getCharacterByName(String name) {

        if (!names.contains(name)) {
            client.getCharactersWithInputName(name);
        }
        names.add(name);
        List<Character> allCharactersWithName = characterService.getAllCharactersWithName(name);

        return allCharactersWithName
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
