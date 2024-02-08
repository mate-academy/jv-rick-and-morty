package mate.academy.rickandmorty.service.character;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharactersDto;
import mate.academy.rickandmorty.mapper.internal.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        return characterMapper.toModel(characterRepository.getRandomCharacter());
    }

    @Override
    public CharactersDto getCharactersBySearchString(String searchString) {
        List<CharacterDto> dtos = characterRepository.findCharactersBySearchString(searchString)
                .stream()
                .map(characterMapper::toModel).toList();
        return new CharactersDto(dtos);
    }
}
