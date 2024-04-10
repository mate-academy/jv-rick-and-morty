package mate.academy.rickandmorty.service.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.request.ApiCharacterRequestDto;
import mate.academy.rickandmorty.dto.response.CharacterResponseDto;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterResponseDto toDto(CartoonCharacter cartoonCharacter);

    CartoonCharacter toModel(ApiCharacterRequestDto requestDto);
}
