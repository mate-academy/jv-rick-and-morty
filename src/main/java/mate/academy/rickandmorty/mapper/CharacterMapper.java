package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterInformationDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    @Mapping(target = "externalId", source = "characterInformationDto.id")
    @Mapping(target = "id", ignore = true)
    Character toModel(CharacterInformationDto characterInformationDto);
}
