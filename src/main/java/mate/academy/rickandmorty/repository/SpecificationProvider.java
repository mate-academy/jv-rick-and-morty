package mate.academy.rickandmorty.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<E> {

    String getKey();

    Specification<E> getSpecification(String[] params);
}
