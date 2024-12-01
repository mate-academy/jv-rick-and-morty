package mate.academy.rickandmorty.repository;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterSpecificationBuilder implements SpecificationBuilder<Character> {
    private static final String NAME_KEY = "name";
    private final SpecificationProviderManager<Character> characterSpecificationProviderManager;

    @Override
    public Specification<Character> build(CharacterSearchParameters searchParameters) {
        Specification<Character> spec = Specification.where(null);
        if (searchParameters.names() != null && searchParameters.names().length > 0) {
            spec = spec.and(characterSpecificationProviderManager.getSpecificationProvider(NAME_KEY)
                    .getSpecification(searchParameters.names()));
        }
        return spec;
    }
}
