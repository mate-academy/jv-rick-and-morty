package mate.academy.rickandmorty.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.episode.EpisodeDto;
import mate.academy.rickandmorty.dto.external.episode.EpisodeSearchParameters;
import mate.academy.rickandmorty.dto.external.episode.EpisodesDto;
import mate.academy.rickandmorty.service.client.EpisodeClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/episode")
public class EpisodeController {
    private final EpisodeClient episodeClient;

    @GetMapping
    public EpisodesDto getCharacters() throws IOException, InterruptedException {
        return episodeClient.getCharacters();
    }

    @GetMapping("/{ids}")
    public List<EpisodeDto> getCharacterByIds(@PathVariable Integer[] ids)
            throws IOException, InterruptedException {
        return episodeClient.getCharactersByIds(ids);
    }

    @GetMapping("/search")
    public EpisodesDto getCharactersByParams(EpisodeSearchParameters episodeSearchParameters)
            throws IOException, InterruptedException {
        return episodeClient.getCharactersByParams(episodeSearchParameters);
    }
}
