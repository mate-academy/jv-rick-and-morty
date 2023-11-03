package mate.academy.rickandmorty.repository.character;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterSearchParametersDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterSpecificationBuilder implements
        SpecificationBuilder<Character, CharacterSearchParametersDto> {
    private final SpecificationProviderManager<Character> providerManager;

    @Override
    public Specification<Character> build(CharacterSearchParametersDto searchParameters) {
        Specification<Character> specification = Specification.where(null);

        if (searchParameters.name() != null && searchParameters.name().length > 0) {
            specification = specification.and(providerManager.getSpecificationProvider("name")
                    .getSpecification(searchParameters.name()));
        }

        if (searchParameters.status() != null && searchParameters.status().length > 0) {
            specification = specification.and(providerManager.getSpecificationProvider("status")
                    .getSpecification(searchParameters.status()));
        }

        if (searchParameters.gender() != null && searchParameters.gender().length > 0) {
            specification = specification.and(providerManager.getSpecificationProvider("gender")
                    .getSpecification(searchParameters.gender()));
        }

        return specification;
    }
}
