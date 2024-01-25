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
public class CharacterServiceClass {
    private final CharactersApiClient charactersApiClient;
    private final CharacterMapper characterMapper;
    private final CharactersRepository charactersRepository;

    public List<Character> saveCharacters(
            List<CharacterFromExternalApiDto> characterFromExternalApiDtoList
    ) {
        List<Character> characterList =
                convertListOfExternalDtoToModel(characterFromExternalApiDtoList);
        charactersRepository.saveAll(characterList);
        return characterList;
    }

    public List<Character> convertListOfExternalDtoToModel(
            List<CharacterFromExternalApiDto> characterFromExternalApiDtoList) {
        return characterFromExternalApiDtoList.stream()
                .map(characterMapper::toModel)
                .toList();
    }
}
