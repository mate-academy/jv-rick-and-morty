package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    
    @Override
    public CharacterResponseDto getCharacter(Long id) {
        int countOfAllCharacters = characterRepository.getCountOfAllCharacters();
        if (id > countOfAllCharacters) {
            throw new RuntimeException("A character with id " + id + " is not exist");
        }
        Long randomId = new Random().nextLong(countOfAllCharacters);
        return characterMapper.toDto(characterRepository.getReferenceById(randomId)).setId(id);
    }
    
    @Override
    public void saveAll(List<CharacterInfoDto> dtos) {
        List<Character> list = dtos.stream().map(characterMapper::toModel).toList();
        characterRepository.saveAll(list);
    }
}
