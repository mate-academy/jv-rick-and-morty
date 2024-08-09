package mate.academy.rickandmorty.repository.character.spec;

import jakarta.persistence.criteria.Predicate;
import java.util.Arrays;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider implements SpecificationProvider<Character> {
    private static final String NAME_KEY = "name";
    private static final String WILDCARD = "%";

    @Override
    public String getKey() {
        return NAME_KEY;
    }

    public Specification<Character> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Predicate[] predicates = Arrays.stream(params)
                    .map(param -> criteriaBuilder.like(
                            root.get(NAME_KEY),
                            WILDCARD + param + WILDCARD
                            )
                    )
                    .toArray(Predicate[]::new);
            return criteriaBuilder.or(predicates);
        };
    }
}
