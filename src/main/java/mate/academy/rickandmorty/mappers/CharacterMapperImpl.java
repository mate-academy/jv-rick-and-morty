package mate.academy.rickandmorty.mappers;

import mate.academy.rickandmorty.dto.CharacterDtoResponse;
import mate.academy.rickandmorty.models.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperImpl implements CharacterMapper {
    public CharacterMapperImpl() {
    }

    public Character toModel(CharacterDtoResponse rickMortyDtoRequest) {
        if (rickMortyDtoRequest == null) {
            return null;
        } else {
            Character character = new Character();
            if (rickMortyDtoRequest.getExternalId() != null) {
                character.setExternalId(rickMortyDtoRequest.getExternalId());
            }

            if (rickMortyDtoRequest.getName() != null) {
                character.setName(rickMortyDtoRequest.getName());
            }

            if (rickMortyDtoRequest.getStatus() != null) {
                character.setStatus(rickMortyDtoRequest.getStatus());
            }

            if (rickMortyDtoRequest.getGender() != null) {
                character.setGender(rickMortyDtoRequest.getGender());
            }

            return character;
        }
    }
}

