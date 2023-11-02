package mate.academy.rickandmorty.repository.character.spec;

import jakarta.persistence.criteria.Predicate;
import java.util.Arrays;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class StatusSpecificationProvider implements SpecificationProvider<Character> {
    @Override
    public Specification<Character> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) -> {
            Predicate[] predicates = Arrays.stream(params)
                    .map(param -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("status")),
                            "%" + param + "%"))
                    .toArray(Predicate[]::new);
            return criteriaBuilder.or(predicates);
        });
    }

    @Override
    public String getKey() {
        return "status";
    }
}
