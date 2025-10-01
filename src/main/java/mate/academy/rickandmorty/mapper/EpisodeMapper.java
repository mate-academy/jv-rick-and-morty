package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.model.Episode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EpisodeMapper {

    default Episode fromString(String url) {
        Episode episode = new Episode();
        episode.setName(url);
        return episode;
    }

    default String toString(Episode episode) {
        return episode.getName();
    }

}
