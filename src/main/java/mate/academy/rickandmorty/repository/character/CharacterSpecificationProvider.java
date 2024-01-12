package mate.academy.rickandmorty.repository.character;

import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CharacterSpecificationProvider implements SpecificationProvider {
    @Override
    public Specification<Character> getSpecification(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}
