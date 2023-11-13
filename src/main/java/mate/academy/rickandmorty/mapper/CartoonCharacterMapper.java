package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CartoonCharacterDto;
import mate.academy.rickandmorty.dto.CreateCartoonCharacterRequestDto;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface CartoonCharacterMapper {
    CartoonCharacterDto toDto(CartoonCharacter book);

    CartoonCharacter toModel(CreateCartoonCharacterRequestDto createBookRequestDto);
}
