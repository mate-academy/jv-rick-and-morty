package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.dto.api.SingleCharacterDataDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    Character toModel(CreateCharacterRequestDto requestDto);

    @Mapping(source = "id", target = "externalId")
    CreateCharacterRequestDto toRequestDto(SingleCharacterDataDto singleCharacter);
}
