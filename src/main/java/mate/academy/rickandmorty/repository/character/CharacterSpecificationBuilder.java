package mate.academy.rickandmorty.repository.character;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParams;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterSpecificationBuilder implements
        SpecificationBuilder<Character, CharacterSearchParams> {
    private final SpecificationProviderManager<Character> characterSpecificationProviderManager;

    @Override
    public Specification<Character> build(CharacterSearchParams searchParams) {
        Specification<Character> spec = Specification.where(null);
        if (searchParams.names() != null && searchParams.names().length > 0) {
            spec = spec.and(characterSpecificationProviderManager
                    .getSpecificationProvider("name")
                    .getSpecification(searchParams.names()));
        }
        return spec;
    }
}
