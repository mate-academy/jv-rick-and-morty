package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.AnimationCharacterResponseDto;
import mate.academy.rickandmorty.mapper.AnimationCharacterMapper;
import mate.academy.rickandmorty.model.AnimationCharacter;
import mate.academy.rickandmorty.repository.AnimationCharacterRepository;
import mate.academy.rickandmorty.service.AnimationCharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnimationCharacterServiceImpl implements AnimationCharacterService {
    private final AnimationCharacterRepository repository;
    private final AnimationCharacterMapper mapper;

    @Override
    public List<AnimationCharacterResponseDto> findAllBySearchString(String searchString) {
        return repository
                .findAllByNameLike("%" + searchString + "%")
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public AnimationCharacterResponseDto getRandomCharacter() {
        Long countOfRecords = repository.getCountOfRecords();
        Long randomId = new Random().nextLong(1L, countOfRecords);
        Optional<AnimationCharacter> optionalCharacter = repository.findById(randomId);
        if (optionalCharacter.isPresent()) {
            return mapper.toDto(optionalCharacter.get());
        }
        throw new RuntimeException("Can't find character by id: " + randomId);
    }
}
