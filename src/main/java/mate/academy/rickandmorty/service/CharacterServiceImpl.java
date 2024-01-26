package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFromExternalApiDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mappers.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharactersRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharactersRepository charactersRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto findAnyCharacter() {
        return characterMapper.toDto(charactersRepository.findAnyCharacter());
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        List<Character> matchedCharacterList = charactersRepository.findByName(name);
        return matchedCharacterList.stream()
                .map(characterMapper::toDto)
                .toList();
    }

    public List<Character> saveCharacters(
            List<CharacterFromExternalApiDto> characterFromExternalApiDtoList
    ) {
        List<Character> characterList =
                convertListOfExternalDtoToModel(characterFromExternalApiDtoList);
        charactersRepository.saveAll(characterList);
        return characterList;
    }

    public List<Character> convertListOfExternalDtoToModel(
            List<CharacterFromExternalApiDto> characterFromExternalApiDtoList
    ) {
        return characterFromExternalApiDtoList.stream()
                .map(characterMapper::toModel)
                .toList();
    }
}
