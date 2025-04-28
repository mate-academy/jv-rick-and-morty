package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.dto.search.CharacterSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecBuilder<T> {
    Specification<T> buildSpec(CharacterSearchParameters searchParameters);
}
