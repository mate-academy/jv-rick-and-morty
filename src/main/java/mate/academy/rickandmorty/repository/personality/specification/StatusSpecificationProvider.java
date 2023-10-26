package mate.academy.rickandmorty.repository.personality.specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.model.Personality;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class StatusSpecificationProvider implements SpecificationProvider<Personality> {
    private static final String STATUS_KEY = "status";

    @Override
    public String getKey() {
        return STATUS_KEY;
    }

    @Override
    public Specification<Personality> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> statusPredicates = new ArrayList<>();
            for (String param : params) {
                statusPredicates.add(criteriaBuilder.like(root.get(STATUS_KEY), "%" + param + "%"));
            }
            return criteriaBuilder.or(statusPredicates.toArray(new Predicate[0]));
        };
    }
}
