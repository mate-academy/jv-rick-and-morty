package mate.academy.rickandmorty.service.client;

import java.io.IOException;
import java.util.List;
import mate.academy.rickandmorty.dto.external.episode.EpisodeDto;
import mate.academy.rickandmorty.dto.external.episode.EpisodeSearchParameters;
import mate.academy.rickandmorty.dto.external.episode.EpisodesDto;

public interface EpisodeClient {
    EpisodesDto getEpisodes() throws IOException, InterruptedException;

    List<EpisodeDto> getEpisodesByIds(Integer[] ids) throws IOException, InterruptedException;

    EpisodesDto getEpisodesByParams(EpisodeSearchParameters episodeSearchParameters)
            throws IOException, InterruptedException;
}
