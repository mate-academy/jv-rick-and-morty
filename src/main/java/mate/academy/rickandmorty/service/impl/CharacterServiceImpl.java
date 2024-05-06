package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ResponseDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final Random random = new Random();
    private final CharacterRepository repository;
    @Qualifier("characterMapperImpl")
    private final CharacterMapper mapper;

    @Override
    public List<ResponseDto> getCharactersByName(String name, Pageable pageable) {
        return repository.findAllByNameContainingIgnoreCase(name).stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public ResponseDto getRandomCharacter() {
        return mapper.toResponseDto(repository.findById(random.nextLong(countCharactersInDb()))
                .orElseThrow(() -> new EntityNotFoundException(
                        "Failed to get random Character from DB")));
    }

    private Long countCharactersInDb() {
        return repository.count();
    }
}
