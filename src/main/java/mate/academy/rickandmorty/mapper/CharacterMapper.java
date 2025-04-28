package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.ExtCharacterRecordDto;
import mate.academy.rickandmorty.dto.internal.RnMCharacterDto;
import mate.academy.rickandmorty.model.RnMCharacter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CharacterMapper {
    RnMCharacter toEntity(ExtCharacterRecordDto recordDto);

    RnMCharacterDto toDto(RnMCharacter character);
}
