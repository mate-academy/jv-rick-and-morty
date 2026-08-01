package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Figure;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider implements SpecificationProvider<Figure> {

    @Override
    public String getKey() {
        return "name";
    }

    public Specification<Figure> getSpecification(String[] params) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + params[0].toLowerCase() + "%");
    }
}
