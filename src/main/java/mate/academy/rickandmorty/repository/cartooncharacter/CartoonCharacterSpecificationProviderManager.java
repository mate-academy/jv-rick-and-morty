package mate.academy.rickandmorty.repository.cartooncharacter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartoonCharacterSpecificationProviderManager
        implements SpecificationProviderManager<CartoonCharacter> {
    private final List<SpecificationProvider<CartoonCharacter>> specificationProviders;

    @Override
    public SpecificationProvider<CartoonCharacter> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(specificationProviders
                        -> specificationProviders.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("Can't get specification provider"
                                + " for key: " + key));
    }
}
