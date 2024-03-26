package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.model.CharacterPerson;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(CharacterPerson character);

    CharacterPerson toModel(CharacterRequestDto requestDto);

    List<CharacterDto> toListDtos(List<CharacterPerson> characters);

    List<CharacterPerson> toListModels(List<CharacterRequestDto> requestDtoList);
}
