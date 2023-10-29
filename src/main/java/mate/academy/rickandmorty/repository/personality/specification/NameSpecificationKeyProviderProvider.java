package mate.academy.rickandmorty.repository.personality.specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.model.Personality;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationKeyProviderProvider
        implements SpecificationProvider<Personality>, SpecificationKeyConstantsProvider {
    @Override
    public String getKey() {
        return NAME_KEY;
    }

    @Override
    public Specification<Personality> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> namePredicates = new ArrayList<>();
            for (String param : params) {
                namePredicates.add(criteriaBuilder.like(root.get(NAME_KEY), "%" + param + "%"));
            }
            return criteriaBuilder.or(namePredicates.toArray(new Predicate[0]));
        };
    }
}
