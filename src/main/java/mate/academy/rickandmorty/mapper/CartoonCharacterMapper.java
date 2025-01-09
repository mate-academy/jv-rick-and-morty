package mate.academy.rickandmorty.mapper;

import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CartoonCharacterInputDataDto;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterCreateRequestDto;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterDto;
import mate.academy.rickandmorty.dto.internal.EpisodeCreateRequestDto;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {LocationMapper.class})
public interface CartoonCharacterMapper {
    CartoonCharacterDto toDto(CartoonCharacter cartoonCharacter);

    CartoonCharacter toModel(CartoonCharacterCreateRequestDto cartoonCharacterCreateRequestDto);

    @Mapping(source = "episodes", target = "episodes",
            qualifiedByName = "fromStringsListToNewEpisodesList")
    CartoonCharacterCreateRequestDto toModel(
            CartoonCharacterInputDataDto cartoonCharacterInputDataDto);

    @Named("fromStringsListToNewEpisodesList")
    default List<EpisodeCreateRequestDto> fromStringsListToNewEpisodesList(List<String> episodes) {
        if (episodes == null || episodes.isEmpty()) {
            return null;
        }

        ArrayList<EpisodeCreateRequestDto> episodeCreateRequestDtos
                = new ArrayList<>();

        for (String episode : episodes) {
            if (episode != null && !episode.isEmpty()) {
                EpisodeCreateRequestDto episodeCreateRequestDto = new EpisodeCreateRequestDto();
                episodeCreateRequestDto.setUrl(episode);
                episodeCreateRequestDtos.add(episodeCreateRequestDto);
            }
        }
        return episodeCreateRequestDtos;
    }
}
