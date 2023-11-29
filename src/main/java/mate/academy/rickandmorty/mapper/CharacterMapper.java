package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(CharacterEntity characterEntity);

    List<CharacterDto> toDtoList(List<CharacterEntity> characterEntities);

    List<CharacterEntity> toEntityList(List<CharacterDto> characterDtos);
}
