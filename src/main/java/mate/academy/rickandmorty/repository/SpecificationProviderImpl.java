package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecificationProviderImpl implements SpecificationProvider {

    @Override
    public Specification<Character> getSpecification(String name) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + name.toLowerCase() + "%"));
    }
}
