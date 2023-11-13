package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CartoonCharacterDto;
import mate.academy.rickandmorty.dto.CreateCartoonCharacterRequestDto;
import org.springframework.data.domain.Pageable;

public interface CartoonCharacterService {
    CartoonCharacterDto save(CreateCartoonCharacterRequestDto requestDto);

    void saveAll(List<CreateCartoonCharacterRequestDto> requestDtoList);

    List<CartoonCharacterDto> findByName(String name, Pageable pageable);

    CartoonCharacterDto getRandom();
}
