package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiCharacterResultDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharactersRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterService {
    private final CharactersRepository charactersRepository;
    private final CharacterMapper characterMapper;

    public CharacterResponseDto getRandomCharacter() {
        long count = charactersRepository.count();
        if (count > 0) {
            Random random = new Random();
            long randomIndex = random.nextInt((int) count + 1);
            return characterMapper.toDto(charactersRepository.findById(randomIndex).orElseThrow(
                    () -> new NoSuchElementException("Can't find random character")));
        } else {
            throw new EntityNotFoundException("No movie characters found in DB");
        }
    }

    public List<CharacterResponseDto> getByName(String name) {
        return charactersRepository.findAllByNameContaining(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Transactional
    public void saveAll(List<ApiCharacterResultDataDto> characterResultDto) {
        List<Character> movieCharacterList = characterResultDto.stream()
                .map(characterMapper::toModel)
                .toList();
        charactersRepository.saveAll(movieCharacterList);
    }

}
