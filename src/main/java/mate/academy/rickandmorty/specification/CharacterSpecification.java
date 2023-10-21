package mate.academy.rickandmorty.specification;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.domain.Specification;

public class CharacterSpecification {
    public static Specification<Character> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("name"), "%" + name + "%");
    }
}
