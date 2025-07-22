package mate.academy.rickandmorty.repository.character;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;
import mate.academy.rickandmorty.model.RickAndMorty;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortySpecificationBuilder implements SpecificationBuilder<RickAndMorty> {

    private static final String NAME = "name";
    private final SpecificationProviderManager<RickAndMorty> specificationProviderManager;

    @Override
    public Specification<RickAndMorty> build(CharacterSearchParametersDto searchParametersDto) {
        Specification<RickAndMorty> spec = Specification.where(null);
        if (searchParametersDto.name() != null && searchParametersDto.name().length > 0) {
            spec = spec.and(specificationProviderManager.getSpecificationProvider(NAME)
                    .getSpecification(searchParametersDto.name()));
        }
        return spec;
    }
}
