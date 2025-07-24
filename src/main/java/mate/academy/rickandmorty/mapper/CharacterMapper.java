package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    CharacterEntity toEntity(ExternalCharacterDto dto);

    CharacterDto toDto(CharacterEntity entity);
}
