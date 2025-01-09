package mate.academy.rickandmorty.service.internal;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterCreateRequestDto;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterDto;
import mate.academy.rickandmorty.mapper.CartoonCharacterMapper;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.repository.CartoonCharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartoonCharacterServiceImpl implements CartoonCharacterService {
    private static final int NUMBER_OF_CHARACTERS = 826;
    private final Random random = new Random();
    private final CartoonCharacterRepository cartoonCharacterRepository;
    private final CartoonCharacterMapper cartoonCharacterMapper;
    private final EpisodeService episodeService;
    private final LocationService locationService;

    @Override
    public CartoonCharacter save(
            CartoonCharacterCreateRequestDto cartoonCharacterCreateRequestDto) {

        if (cartoonCharacterRepository.findByExternalId(
                cartoonCharacterCreateRequestDto.getExternalId()).isPresent()) {
            return
                    cartoonCharacterRepository.findByExternalId(
                            cartoonCharacterCreateRequestDto.getExternalId()).get();
        }

        CartoonCharacter cartoonCharacter
                = cartoonCharacterMapper.toModel(cartoonCharacterCreateRequestDto);
        cartoonCharacter.setEpisodes(
                episodeService.save(cartoonCharacterCreateRequestDto.getEpisodes()));
        cartoonCharacter.setOrigin(
                locationService.save(cartoonCharacterCreateRequestDto.getOrigin()));
        cartoonCharacter.setLocation(
                locationService.save(cartoonCharacterCreateRequestDto.getLocation()));

        if (cartoonCharacterRepository.findByName(
                cartoonCharacter.getName()).isPresent()) {
            cartoonCharacter.setId(
                    cartoonCharacterRepository
                            .findByName(cartoonCharacter.getName()).get().getId()
            );
        }
        return cartoonCharacterRepository.save(cartoonCharacter);
    }

    @Override
    public CartoonCharacterDto getCartoonCharacter(Long id) {
        return cartoonCharacterMapper.toDto(
                cartoonCharacterRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Character with id: " + id + " not found")));
    }

    @Override
    public CartoonCharacterDto getRandomCartoonCharacter() {
        return getCartoonCharacter(random.nextLong(NUMBER_OF_CHARACTERS));
    }

    @Override
    public List<CartoonCharacterDto> findNamesWhichContainString(String string) {
        return cartoonCharacterRepository.findNamesLike("%" + string + "%")
                .stream()
                .map(cartoonCharacterMapper::toDto)
                .toList();
    }
}
