package mate.academy.rickandmorty.repository.specification;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Personage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecificationBuilderImpl implements SpecificationBuilder {
    private final SpecificationProvider provider;

    @Override
    public Specification<Personage> buildSpecification(String name) {
        Specification<Personage> spec = Specification.where(null);
        if (parameterValidator(name)) {
            spec = spec.and(provider.getSpecification(name));
        }

        return spec;
    }

    private boolean parameterValidator(String parameter) {
        return parameter != null && !parameter.isEmpty();
    }
}
