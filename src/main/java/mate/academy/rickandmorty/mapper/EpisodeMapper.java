package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.internal.EpisodeCreateRequestDto;
import mate.academy.rickandmorty.dto.internal.EpisodeDto;
import mate.academy.rickandmorty.model.Episode;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface EpisodeMapper {
    EpisodeDto toDto(Episode episode);

    Episode toModel(EpisodeCreateRequestDto episodeCreateRequestDto);
}
