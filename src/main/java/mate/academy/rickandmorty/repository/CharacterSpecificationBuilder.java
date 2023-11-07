package mate.academy.rickandmorty.repository;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParam;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.user.NameSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterSpecificationBuilder implements SpecificationBuilder<Character> {
    private final NameSpecification nameSpecification;

    @Override
    public Specification<Character> build(CharacterSearchParam param) {
        Specification<Character> spec = Specification.where(null);
        if (param.name() != null && !param.name().isEmpty()) {
            spec = spec.and(nameSpecification.getSpecification(param.name()));
        }
        return spec;
    }
}
