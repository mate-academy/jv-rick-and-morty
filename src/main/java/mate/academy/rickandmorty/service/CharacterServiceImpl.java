package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRequestDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;

    @Override
    public CharacterRequestDto getRandomCharacter() {
        Random random = new Random();
        int id = random.nextInt((int) characterRepository.count());
        return mapper.toDto(characterRepository.getReferenceById((long) id));
    }

    @Override
    public List<CharacterRequestDto> findByName(String name) {
        return characterRepository.findAllByNameContainsIgnoreCase(name).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void saveAll(List<CharacterResponseDto> characterResponseDtos) {
        for (CharacterResponseDto character : characterResponseDtos) {
            characterRepository.save(mapper.toModel(character));
        }
    }
}
