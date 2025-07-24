package mate.academy.rickandmorty.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    public CharacterDto findRandomCharacter() {
        CharacterEntity entity = characterRepository.findRandom()
                .orElseThrow(() -> new IllegalStateException("No characters available"));
        return characterMapper.toDto(entity);
    }
}
