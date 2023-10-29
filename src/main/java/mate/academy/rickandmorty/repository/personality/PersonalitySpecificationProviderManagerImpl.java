package mate.academy.rickandmorty.repository.personality;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Personality;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalitySpecificationProviderManagerImpl
        implements SpecificationProviderManager<Personality> {
    private final List<SpecificationProvider<Personality>> personalitySpecificationProviders;

    @Override
    public SpecificationProvider<Personality> getSpecificationProvider(String key) {
        return personalitySpecificationProviders.stream()
                .filter(b -> b.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specification provider for key " + key));
    }
}
