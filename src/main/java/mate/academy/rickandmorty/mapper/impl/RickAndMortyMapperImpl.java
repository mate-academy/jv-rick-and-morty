package mate.academy.rickandmorty.mapper.impl;

import mate.academy.rickandmorty.dto.external.CharacterRickAndMortyDataResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRickAndMortyDto;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.model.RickAndMortyModel;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyMapperImpl implements RickAndMortyMapper {

    @Override
    public RickAndMortyModel toModel(CharacterRickAndMortyDataResponseDto responseDto) {
        RickAndMortyModel rickAndMortyModel = new RickAndMortyModel();
        rickAndMortyModel.setName(responseDto.getName());
        rickAndMortyModel.setGender(responseDto.getGender());
        rickAndMortyModel.setExternalId(responseDto.getExternalId());
        rickAndMortyModel.setStatus(responseDto.getStatus());
        return rickAndMortyModel;
    }

    @Override
    public CharacterRickAndMortyDto toDto(RickAndMortyModel rickAndMortyModel) {
        CharacterRickAndMortyDto characterRickAndMortyDto = new CharacterRickAndMortyDto();
        characterRickAndMortyDto.setId(rickAndMortyModel.getId());
        characterRickAndMortyDto.setExternalId(rickAndMortyModel.getExternalId());
        characterRickAndMortyDto.setName(rickAndMortyModel.getName());
        characterRickAndMortyDto.setStatus(rickAndMortyModel.getStatus());
        characterRickAndMortyDto.setGender(rickAndMortyModel.getGender());
        return characterRickAndMortyDto;
    }
}
