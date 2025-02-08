package mate.academy.rickandmorty.repository.cartooncharacter;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.cartoon.CartoonCharacterSearchDto;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartoonCharacterSpecificationBuilder
        implements SpecificationBuilder<CartoonCharacter> {
    private final SpecificationProviderManager<CartoonCharacter> providerManager;

    @Override
    public Specification<CartoonCharacter> build(
            CartoonCharacterSearchDto cartoonCharacterSearchDto
    ) {
        Specification<CartoonCharacter> spec = Specification.where(null);
        if (cartoonCharacterSearchDto.name() != null
                && cartoonCharacterSearchDto.name().length > 0) {
            spec = providerManager.getSpecificationProvider("name")
                    .getSpecification(cartoonCharacterSearchDto.name());
        }
        return spec;
    }
}
