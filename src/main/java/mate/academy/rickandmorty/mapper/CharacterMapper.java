package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterLocationDto;
import mate.academy.rickandmorty.dto.external.CharacterOriginDto;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.CharacterLocation;
import mate.academy.rickandmorty.model.CharacterOrigin;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    public Character toModel(CharacterResultsDto resultsDto) {
        return Character.builder()
                .externalId(resultsDto.getId())
                .name(resultsDto.getName())
                .status(resultsDto.getStatus())
                .species(resultsDto.getSpecies())
                .type(resultsDto.getType())
                .gender(resultsDto.getGender())
                .origin(toModel(resultsDto.getOrigin()))
                .location(toModel(resultsDto.getLocation()))
                .image(resultsDto.getImage())
                .episode(resultsDto.getEpisode())
                .url(resultsDto.getUrl())
                .created(resultsDto.getCreated())
                .build();
    }

    public CharacterOrigin toModel(CharacterOriginDto originDto) {
        return CharacterOrigin.builder()
                .name(originDto.getName())
                .url(originDto.getUrl())
                .build();
    }

    public CharacterLocation toModel(CharacterLocationDto locationDto) {
        return CharacterLocation.builder()
                .name(locationDto.getName())
                .url(locationDto.getUrl())
                .build();
    }

    public CharacterWikiDto toDto(Character character) {
        return CharacterWikiDto.builder()
                .id(character.getId())
                .externalId(character.getExternalId())
                .name(character.getName())
                .status(character.getStatus())
                .gender(character.getGender())
                .build();
    }
}
