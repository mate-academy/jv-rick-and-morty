package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.dto.CharacterSearchParam;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(CharacterSearchParam param);
}
