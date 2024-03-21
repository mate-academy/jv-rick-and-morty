package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private CharacterMapper mapper;
    private CharacterRepository characterRepository;

    @Override
    public CharacterDto getRandomCharacterDto() {
        long size = characterRepository.count();
        Random random = new Random();

        return mapper.toDto(characterRepository.findById(random.nextLong(size) + 1)
                .orElseThrow(() -> new NoSuchElementException(
                        "Cannot get a character by id " + size)));
    }

    @Override
    public void saveAll(List<CharacterResponseDto> characterResponseDtos) {
        characterRepository.saveAll(characterResponseDtos.stream()
                .map(mapper::toModel)
                .toList());
    }
}
