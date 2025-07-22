package mate.academy.rickandmorty.repository.character;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.RickAndMorty;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortySpecificationProviderManager
        implements SpecificationProviderManager<RickAndMorty> {

    private final List<SpecificationProvider<RickAndMorty>> specificationProviders;

    @Override
    public SpecificationProvider<RickAndMorty> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(person -> person.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find specification provider for kay " + key));
    }
}
