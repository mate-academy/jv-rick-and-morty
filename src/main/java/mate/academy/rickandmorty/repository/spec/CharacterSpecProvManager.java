package mate.academy.rickandmorty.repository.spec;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.RnMCharacter;
import mate.academy.rickandmorty.repository.SpecProvManager;
import mate.academy.rickandmorty.repository.SpecProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterSpecProvManager implements SpecProvManager<RnMCharacter> {
    private final List<SpecProvider<RnMCharacter>> specificationProviders;

    @Override
    public SpecProvider<RnMCharacter> getSpecProvider(String key) {
        return specificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't get appropriate provider"
                        + "for key: " + key));
    }
}
