package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Figure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureRepository extends JpaRepository<Figure, Long>,
        JpaSpecificationExecutor<Figure> {
}
