package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    Character toModel(CreateCharacterRequestDto requestDto);
}
