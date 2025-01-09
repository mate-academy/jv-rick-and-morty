package mate.academy.rickandmorty.service.internal;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.EpisodeCreateRequestDto;
import mate.academy.rickandmorty.mapper.EpisodeMapper;
import mate.academy.rickandmorty.model.Episode;
import mate.academy.rickandmorty.repository.EpisodeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;

    @Override
    public List<Episode> save(List<EpisodeCreateRequestDto> episodesCreateRequestDtos) {
        ArrayList<Episode> episodeList = new ArrayList<>();
        for (EpisodeCreateRequestDto episodeCreateRequestDto : episodesCreateRequestDtos) {
            Episode episode = episodeMapper.toModel(episodeCreateRequestDto);
            if (episodeRepository.findByUrl(episode.getUrl()).isPresent()) {
                episode.setId(episodeRepository
                        .findByUrl(episode.getUrl()).get().getId());
            }
            episodeList.add(episodeRepository.save(episode));
        }
        return episodeList;
    }
}
