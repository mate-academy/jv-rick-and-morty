package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.RandMResponseDto;
import mate.academy.rickandmorty.mappers.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void save(RandMResponseDto inputDto) {
        Character entity = characterMapper.toEntity(inputDto);
        Character savedCharacter = characterRepository.save(entity);
        characterMapper.toDto(savedCharacter);
    }

    @Override
    public Optional<Character> findById(Long id) {
        return characterRepository.findById(id);
    }

    @Override
    public List<Character> getAllCharactersWithName(String name) {
        return characterRepository.findAllByNameContainsIgnoreCase(name);
    }

    @Override
    public int getNumberOfCharactersInDb() {
        return characterRepository.getNumberOfCharactersInDb();
    }
}
