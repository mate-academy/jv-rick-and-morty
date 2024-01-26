package mate.academy.rickandmorty.dto.external.episode;

import lombok.Data;
import mate.academy.rickandmorty.model.Info;

@Data
public class EpisodesDto {
    private Info info;
    private EpisodeDto[] results;
}
