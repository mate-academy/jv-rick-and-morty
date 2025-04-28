package mate.academy.rickandmorty.repository.spec.providers;

import mate.academy.rickandmorty.model.RnMCharacter;
import mate.academy.rickandmorty.repository.SpecProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecProvider implements SpecProvider<RnMCharacter> {
    private static final String NAME_PROVIDER_KEY = "name";

    @Override
    public Specification<RnMCharacter> getSpec(String params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(NAME_PROVIDER_KEY), "%" + params + "%");
    }

    @Override
    public String getKey() {
        return NAME_PROVIDER_KEY;
    }
}
