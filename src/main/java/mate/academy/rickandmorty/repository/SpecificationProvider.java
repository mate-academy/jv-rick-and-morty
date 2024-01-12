package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider {
    Specification<Character> getSpecification(String param);
}
