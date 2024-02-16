package mate.academy.rickandmorty.user;

import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecification {
    public Specification<CharacterEntity> getSpecification(String param) {
        return (root, query, criteriaBuilder)
                   -> criteriaBuilder.like(root.get("name"), "%" + param + "%");
    }
}
