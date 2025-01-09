package mate.academy.rickandmorty.repository;

import java.util.Optional;
import mate.academy.rickandmorty.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends
        JpaRepository<Episode, Long>,
        JpaSpecificationExecutor<Episode> {
    Optional<Episode> findByUrl(String url);
}
