package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.CharacterInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    @Mappings({
            @Mapping(source = "dbId", target = "id"),
            @Mapping(source = "id", target = "externalId")
    })
    CharacterResponseDto toDto(CharacterInfo characterInfo);

    List<CharacterResponseDto> toDto(Page<CharacterInfo> characters);
}
