package mate.academy.rickandmorty.config.mapper;

import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.CharacterRickAndMorty;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public interface CharacterMapper {
    @Mapping(source = "id", target = "externalId")
    CharacterRickAndMorty toCharacterModel(CharacterResponseDataDto responseDto);

    CharacterDto toDto(CharacterRickAndMorty characterRickAndMorty);
}
