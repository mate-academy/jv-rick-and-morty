package mate.academy.rickandmorty.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.FigureSearchParametersDto;
import mate.academy.rickandmorty.model.Figure;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FigureSpecificationBuilder implements SpecificationBuilder<Figure> {

    private final SpecificationProviderManager<Figure> specificationProviderManager;

    @Override
    public Specification<Figure> build(FigureSearchParametersDto searchParameters) {
        Specification<Figure> spec = (Root<Figure> root, CriteriaQuery<?> query,
                                    CriteriaBuilder cb) -> cb.conjunction();
        if (searchParameters.name() != null && !searchParameters.name().isEmpty()) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider("name")
                    .getSpecification(searchParameters.name()));
        }

        return spec;
    }
}
