package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharactersDto;
import mate.academy.rickandmorty.mapper.CharactersMapper;
import mate.academy.rickandmorty.model.Characters;
import mate.academy.rickandmorty.repository.CharactersRepository;
import mate.academy.rickandmorty.service.InternalCharacterService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
@Service
public class InternalCharacterServiceImpl implements InternalCharacterService {
    private final CharactersRepository charactersRepository;
    private final CharactersMapper charactersMapper;

    @Override
    public List<CharactersDto> getCharactersByName(String name) {
        List<Characters> characters = charactersRepository.findByNameContaining(name);
        return charactersMapper.listToDto(characters);
    }

    @Override
    public CharactersDto getRandomCharacter() {
        Long randomLong = new Random().nextLong(charactersRepository.count());
        Characters characters = charactersRepository.findById(randomLong)
                .orElseThrow(() -> new RuntimeException(
                        "Occurred an error while pulling random character"));
        CharactersDto charactersDto = charactersMapper.toDto(characters);
        return charactersDto;
    }
}
