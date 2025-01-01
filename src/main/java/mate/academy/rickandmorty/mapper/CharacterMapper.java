package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CartoonCharacter toModel(CharacterResponseDataDto dataDto);

    CharacterResponseDataDto toDto(CartoonCharacter entity);
}
