package mate.academy.rickandmorty.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecProvider<T> {
    Specification<T> getSpec(String params);

    String getKey();
}
