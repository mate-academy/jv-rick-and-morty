package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long characterNumber = characterRepository.count();
        Character characterFromDb = characterRepository.findById(random.nextLong(characterNumber))
                .orElseThrow(
                        () -> new EntityNotFoundException("Failed to find random character!")
                );
        return characterMapper.toInternalResponseDto(characterFromDb);
    }

    @Override
    public List<CharacterResponseDto> getCharactersByNamePart(String namePart) {
        return characterRepository.findAllByNameLikeIgnoreCase(namePart)
                .stream().map(characterMapper::toInternalResponseDto)
                .toList();
    }
}
