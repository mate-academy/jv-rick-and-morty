package mate.academy.rickandmorty.repository.character;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class CharacterSpecificationBuilder implements
        SpecificationBuilder<Character, CharacterSearchParameters> {
    private final SpecificationProviderManager<Character> characterSpecificationProviderManager;

    @Override
    public Specification<Character> build(CharacterSearchParameters searchParams) {
        Specification<Character> spec = Specification.where(null);
        if (searchParams.getNames() != null && searchParams.getNames().length > 0) {
            spec = spec.and(characterSpecificationProviderManager
                    .getSpecificationProvider("name")
                    .getSpecification(searchParams.getNames()));
        }
        return spec;
    }
}
