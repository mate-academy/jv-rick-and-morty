package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterEntity toModel(CharacterDto requestDto);

    List<CharacterEntity> toListModel(List<CharacterDto> requestDto);

    @AfterMapping
    default void setExternalId(CharacterDto requestDto, @MappingTarget CharacterEntity entity) {
        entity.setExternalId(requestDto.getExternalId());
    }
}
