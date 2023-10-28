package mate.academy.rickandmorty.repository.personality;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonalitySearchParametersDto;
import mate.academy.rickandmorty.model.Personality;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalitySpecificationBuilderImpl implements SpecificationBuilder<Personality> {
    private static final String NAME_KEY = "name";

    @Autowired
    private final SpecificationProviderManager<Personality> personalitySpecificationProviderManager;

    @Override
    public Specification<Personality> build(PersonalitySearchParametersDto searchParameters) {
        Specification<Personality> spec = Specification.where(null);
        if (searchParameters.name() != null && searchParameters.name().length > 0) {
            spec = spec.and(personalitySpecificationProviderManager
                    .getSpecificationProvider(NAME_KEY)
                    .getSpecification(searchParameters.name()));
        }
        return spec;
    }
}
