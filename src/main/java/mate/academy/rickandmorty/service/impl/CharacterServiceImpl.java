package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapping.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<CharacterDto> findByName(Pageable pageable, String name) {
        return characterRepository.findByNameContaining(pageable, name)
                .stream()
                .map(characterMapper::convertToDto)
                .toList();
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return characterMapper.convertToDto(
                characterRepository.findRandomCharacter()
                .orElseThrow(() -> new NoSuchElementException("Random character not found")));
    }
}
