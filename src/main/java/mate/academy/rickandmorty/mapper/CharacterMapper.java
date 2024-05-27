package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapStructConfig;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CreateCharacterRequestDto;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(CartoonCharacter character);

    CartoonCharacter toModel(CreateCharacterRequestDto requestDto);
}
