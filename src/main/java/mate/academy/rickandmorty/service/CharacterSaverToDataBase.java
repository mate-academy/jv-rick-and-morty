package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFromExternalApiDto;
import mate.academy.rickandmorty.mappers.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharactersRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterSaverToDataBase {
    private final CharactersApiClient charactersApiClient;
    private final CharacterMapper characterMapper;
    private final CharactersRepository charactersRepository;

    public List<Character> convertListOfExternalDtoToModel(
            List<CharacterFromExternalApiDto> characterFromExternalApiDtoList) {
        return characterFromExternalApiDtoList.stream()
                .map(characterMapper::toModel)
                .toList();
    }

    public List<Character> saveCharacterModelListToDataBase(List<Character> characterList) {
        charactersRepository.saveAll(characterList);
        return characterList;
    }
}
