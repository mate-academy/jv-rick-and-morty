package mate.academy.rickandmorty.service;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterOfRickyAndMortyDto;
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
    public CharacterOfRickyAndMortyDto save(RandMResponseDto inputDto) {
        Character entity = characterMapper.toEntity(inputDto);
        Character savedCharacter = characterRepository.save(entity);
        return characterMapper.toDto(savedCharacter);
    }

    @Override
    public List<BigDecimal> findAllExternalId() {
        return characterRepository.findAllExternalId();
    }

    @Override
    public RandMResponseDto findByExternalId(BigDecimal externalId) {
        return characterRepository.findByExternalId(externalId);
    }

    @Override
    public List<Character> getAllCharactersWithName(String name) {
        return characterRepository.findAllByNameContainsIgnoreCase(name);
    }
}
