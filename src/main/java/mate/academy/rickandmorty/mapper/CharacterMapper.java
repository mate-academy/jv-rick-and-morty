package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.AppConfig;
import mate.academy.rickandmorty.dto.external.character.CharacterInfoDto;
import mate.academy.rickandmorty.dto.internal.cartoon.CartoonCharacterDto;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.mapstruct.Mapper;

@Mapper(config = AppConfig.class)
public interface CharacterMapper {

    CartoonCharacterDto toDto(CartoonCharacter character);

    CartoonCharacter toModel(CharacterInfoDto characterInfoDto);
}
