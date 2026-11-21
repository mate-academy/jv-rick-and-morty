package mate.academy.rickandmorty.repository.specification;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecification {
    private static final String NAME = "name";

    public Specification<Character> getSpecification(String param) {
        return (root, query, cb) -> cb.like(
                cb.lower(root.get(NAME)),
                "%" + param.toLowerCase() + "%"
        );
    }
}
