package mate.academy.rickandmorty.repository.character.specification;

import java.util.Arrays;
import mate.academy.rickandmorty.model.RickAndMorty;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider implements SpecificationProvider<RickAndMorty> {
    @Override
    public String getKey() {
        return "name";
    }

    @Override
    public Specification<RickAndMorty> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) -> root.get("name")
                .in(Arrays.stream(params).toArray()));
    }
}
