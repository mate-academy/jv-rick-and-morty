package mate.academy.rickandmorty.services;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.cartoon.CartoonCharacterDto;
import mate.academy.rickandmorty.dto.internal.cartoon.CartoonCharacterSearchDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import mate.academy.rickandmorty.repository.cartooncharacter.CartoonCharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CartoonCharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final SpecificationBuilder<CartoonCharacter> specificationBuilder;

    @Override
    public void save(CartoonCharacter cartoonCharacter) {
        characterRepository.save(cartoonCharacter);
    }

    @Override
    public List<CartoonCharacterDto> findAll(Pageable pageable) {
        return characterRepository.findAll(pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public List<CartoonCharacterDto> searchAllByName(
            CartoonCharacterSearchDto cartoonCharacterSearchDto
    ) {
        Specification<CartoonCharacter> specification =
                specificationBuilder.build(cartoonCharacterSearchDto);
        return characterRepository.findAll(specification).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public CartoonCharacterDto randomCharacter() {
        Random random = new Random();
        List<CartoonCharacterDto> characters = characterRepository.findAll().stream()
                .map(characterMapper::toDto)
                .toList();
        return characters.get(random.nextInt(characters.size()));
    }
}
