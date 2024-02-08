package mate.academy.rickandmorty.mapper.external;

import mate.academy.rickandmorty.dto.external.CharacterApiDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyApiMapperImpl implements RickAndMortyApiMapper {

    @Override
    public Character toDto(CharacterApiDto characterApiDto) {
        Character character = new Character();
        character.setExternalId(characterApiDto.getExternalId());
        character.setGender(characterApiDto.getGender());
        character.setName(characterApiDto.getName());
        character.setStatus(characterApiDto.getStatus());
        return character;
    }
}
