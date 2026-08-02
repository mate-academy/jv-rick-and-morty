package mate.academy.rickandmorty.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Figure;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FigureSpecificationProviderManager implements SpecificationProviderManager<Figure> {

    private final List<SpecificationProvider<Figure>> figureSpecificationProviders;

    @Override
    public SpecificationProvider<Figure> getSpecificationProvider(String key) {
        return figureSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Can't find specification provider for key: " + key));
    }
}
