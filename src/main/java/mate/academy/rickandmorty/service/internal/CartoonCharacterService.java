package mate.academy.rickandmorty.service.internal;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterCreateRequestDto;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterDto;
import mate.academy.rickandmorty.model.CartoonCharacter;

public interface CartoonCharacterService {
    CartoonCharacter save(CartoonCharacterCreateRequestDto cartoonCharacterCreateRequestDto);

    CartoonCharacterDto getCartoonCharacter(Long id);

    CartoonCharacterDto getRandomCartoonCharacter();

    List<CartoonCharacterDto> findNamesWhichContainString(String string);
}
