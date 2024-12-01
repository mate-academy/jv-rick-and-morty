package mate.academy.rickandmorty.repository.character;

import java.util.Arrays;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider implements SpecificationProvider<Character> {
    private static final String NAME_KEY = "name";

    @Override
    public String getKey() {
        return NAME_KEY;
    }

    @Override
    public Specification<Character> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(NAME_KEY)
                        .in(Arrays.stream(params).toArray());
    }
}
