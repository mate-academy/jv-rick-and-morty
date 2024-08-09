package mate.academy.rickandmorty.repository.character;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterSpecificationBuilder implements SpecificationBuilder<Character> {
    private final SpecificationProviderManager<Character> specificationProviderManager;

    @Override
    public Specification<Character> build(CharacterSearchParameters searchParameters) {
        Specification<Character> spec = Specification.where(null);
        if (searchParameters.name() != null && searchParameters.name().length > 0) {
            spec = spec.and(specificationProviderManager.getSpecificationProvider("name")
                    .getSpecification(searchParameters.name()));
        }
        return spec;
    }
}
