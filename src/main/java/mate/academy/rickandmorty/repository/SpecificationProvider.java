package mate.academy.rickandmorty.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    String getKay();

    Specification<T> getSpecification(String[] params);
}
