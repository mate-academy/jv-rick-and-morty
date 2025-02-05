package mate.academy.rickandmorty.repository.specification;

import mate.academy.rickandmorty.model.Personage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecificationProviderImpl implements SpecificationProvider {
    private static final String DETERMINER = "name";

    @Override
    public Specification<Personage> getSpecification(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get(DETERMINER), "%" + name + "%"
        );
    }
}
