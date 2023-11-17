package mate.academy.rickandmorty.repository.user;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecification {
    public Specification<Character> getSpecification(String param) {
        return (root, query, criteriaBuilder)
                   -> criteriaBuilder.like(root.get("name"), "%" + param + "%");
    }
}
