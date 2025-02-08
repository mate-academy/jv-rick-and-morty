package mate.academy.rickandmorty.repository.cartooncharacter.spec;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider implements SpecificationProvider<CartoonCharacter> {
    @Override
    public String getKey() {
        return "name";
    }

    @Override
    public Specification<CartoonCharacter> getSpecification(String[] params) {
        return (root, query, criteriaBuilder)
                -> {
            List<Predicate> predicates = new ArrayList<>();
            for (String param : params) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + param.toLowerCase() + "%"));
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
