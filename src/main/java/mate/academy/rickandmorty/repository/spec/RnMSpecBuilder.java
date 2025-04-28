package mate.academy.rickandmorty.repository.spec;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.search.CharacterSearchParameters;
import mate.academy.rickandmorty.model.RnMCharacter;
import mate.academy.rickandmorty.repository.SpecBuilder;
import mate.academy.rickandmorty.repository.SpecProvManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RnMSpecBuilder implements SpecBuilder<RnMCharacter> {
    private static final String NAME_PROVIDER_KEY = "name";
    private final SpecProvManager<RnMCharacter> characterSpecProvManager;

    @Override
    public Specification<RnMCharacter> buildSpec(CharacterSearchParameters searchParameters) {
        Specification<RnMCharacter> spec = Specification.where(null);
        if (searchParameters.name() != null && !searchParameters.name().isEmpty()) {
            spec = spec.and(characterSpecProvManager
                    .getSpecProvider(NAME_PROVIDER_KEY)
                    .getSpec(searchParameters.name()));
        }
        return spec;
    }
}
