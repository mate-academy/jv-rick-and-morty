package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.dto.internal.PersonalitySearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(PersonalitySearchParametersDto searchParametersDto);
}
