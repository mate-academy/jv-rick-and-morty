package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterRickAndMortyDataResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRickAndMortyDto;
import mate.academy.rickandmorty.model.RickAndMortyModel;

public interface RickAndMortyMapper {

    RickAndMortyModel toModel(CharacterRickAndMortyDataResponseDto responseDto);

    CharacterRickAndMortyDto toDto(RickAndMortyModel rickAndMortyModel);
}
