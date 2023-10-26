package mate.academy.rickandmorty.repository.personality.specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.model.Personality;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class GenderSpecificationProvider implements SpecificationProvider<Personality> {
    private static final String GENDER_KEY = "gender";

    @Override
    public String getKey() {
        return GENDER_KEY;
    }

    @Override
    public Specification<Personality> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> genderPredicates = new ArrayList<>();
            for (String param : params) {
                genderPredicates.add(criteriaBuilder.like(root.get(GENDER_KEY), "%" + param + "%"));
            }
            return criteriaBuilder.or(genderPredicates.toArray(new Predicate[0]));
        };
    }
}
