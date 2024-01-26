package mate.academy.rickandmorty.service.client.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.episode.EpisodeDto;
import mate.academy.rickandmorty.dto.external.episode.EpisodeSearchParameters;
import mate.academy.rickandmorty.dto.external.episode.EpisodesDto;
import mate.academy.rickandmorty.service.client.EpisodeClient;
import mate.academy.rickandmorty.service.http.impl.HttpResponseStringService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EpisodeClientImpl implements EpisodeClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/episode";
    private final ObjectMapper objectMapper;
    private final HttpResponseStringService httpResponseStringService;

    public EpisodesDto getEpisodes() throws IOException, InterruptedException {
        return objectMapper.readValue(httpResponseStringService.getHttpResponse(BASE_URL).body(),
                EpisodesDto.class);
    }

    @Override
    public List<EpisodeDto> getEpisodesByIds(Integer... ids)
            throws IOException, InterruptedException {
        String url = BASE_URL + "/" + Arrays.toString(ids)
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "");
        return ids.length == 1 ? List.of(objectMapper
                .readValue(httpResponseStringService.getHttpResponse(url).body(), EpisodeDto.class))
                : objectMapper.readValue(httpResponseStringService.getHttpResponse(url).body(),
                    new TypeReference<>() {
                    });
    }

    @Override
    public EpisodesDto getEpisodesByParams(EpisodeSearchParameters episodeSearchParameters)
            throws IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL + "/?");
        if (episodeSearchParameters.getName() != null) {
            urlBuilder.append("name=")
                    .append(episodeSearchParameters.getEpisode()
                            .replace(" ", "+")).append("&");
        }
        if (episodeSearchParameters.getEpisode() != null) {
            urlBuilder.append("episode=")
                    .append(episodeSearchParameters.getEpisode()
                            .replace(" ", "+")).append("&");
        }
        return objectMapper.readValue(httpResponseStringService
                .getHttpResponse(urlBuilder.toString()).body(), EpisodesDto.class);
    }
}
