package mate.academy.rickandmorty.services;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.cartoon.CartoonCharacterDto;
import mate.academy.rickandmorty.dto.internal.cartoon.CartoonCharacterSearchDto;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.springframework.data.domain.Pageable;

public interface CharacterService {

    void save(CartoonCharacter cartoonCharacter);

    List<CartoonCharacterDto> findAll(Pageable pageable);

    List<CartoonCharacterDto> searchAllByName(CartoonCharacterSearchDto cartoonCharacterSearchDto);

    CartoonCharacterDto randomCharacter();

}
