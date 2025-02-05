package mate.academy.rickandmorty.repository.specification;

import mate.academy.rickandmorty.model.Personage;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider {
    Specification<Personage> getSpecification(String name);
}
