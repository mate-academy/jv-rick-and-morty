package mate.academy.rickandmorty.service.internal;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.EpisodeCreateRequestDto;
import mate.academy.rickandmorty.model.Episode;

public interface EpisodeService {
    List<Episode> save(List<EpisodeCreateRequestDto> episodesCreateRequestDtos);
}
