package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.Result;
import mate.academy.rickandmorty.dto.internal.PersonageResponseDto;
import mate.academy.rickandmorty.model.Personage;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface PersonageMapper {
    PersonageResponseDto toDto(Personage personage);

    Personage toPersonage(Result result);
}
