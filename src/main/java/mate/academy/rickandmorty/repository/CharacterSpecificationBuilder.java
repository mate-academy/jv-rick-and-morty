package mate.academy.rickandmorty.repository;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParam;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.user.NameSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterSpecificationBuilder implements SpecificationBuilder<CharacterEntity> {
    private final NameSpecification nameSpecification;

    @Override
    public Specification<CharacterEntity> build(CharacterSearchParam param) {
        Specification<CharacterEntity> spec = Specification.where(null);
        if (param.name() != null && !param.name().isEmpty()) {
            spec = spec.and(nameSpecification.getSpecification(param.name()));
        }
        return spec;
    }
}
