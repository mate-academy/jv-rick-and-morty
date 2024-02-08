package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public CharacterDto getRandomCharacter() {
        CharacterDto dto = new CharacterDto();
        Character character = characterRepository.getRandomCharacter();
        dto.setId(character.getId());
        dto.setExternalId(character.getExternalId());
        dto.setName(character.getName());
        dto.setGender(character.getGender());
        dto.setStatus(character.getStatus());
        return dto;
    }

    @Override
    public List<Character> getCharactersBySearchString(String searchString) {
        return characterRepository.findCharactersBySearchString(searchString);
    }
}
