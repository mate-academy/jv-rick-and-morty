package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ResponseCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public ResponseCharacterDto getRandom() {
        return characterMapper.toDto(
                characterRepository.findRandomCharacter().orElseThrow(
                        () -> new EntityNotFoundException("No character in db")
                )
        );
    }

    public List<ResponseCharacterDto> getAllByNames(String namePattern) {
        return characterRepository.getAllByNameIn(namePattern).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
