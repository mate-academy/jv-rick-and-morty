package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterShortInfoDto;
import mate.academy.rickandmorty.mapper.CharacterShortInfoMapper;
import mate.academy.rickandmorty.model.CharacterModel;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {
    private final RickAndMortyRepository rickAndMortyRepository;
    private final CharacterShortInfoMapper characterShortInfoMapper;

    @Override
    public CharacterShortInfoDto getRandomCharacter() {
        Random random = new Random();
        long randomId = random.nextLong(1, rickAndMortyRepository.count() + 1);
        Optional<CharacterModel> byId = rickAndMortyRepository.findById(randomId);
        CharacterModel characterModel = byId.orElseThrow(
                () -> new RuntimeException("No such element with id " + randomId));
        return characterShortInfoMapper.toDto(characterModel);
    }

    @Override
    public List<CharacterShortInfoDto> findByNameContainingIgnoreCase(String name) {
        return rickAndMortyRepository.findByNameContainingIgnoreCase(name).stream()
                .map(characterShortInfoMapper::toDto)
                .toList();
    }
}
