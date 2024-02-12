package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharactersMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final Long PAGE_SIZE = 20L;
    private final CharacterRepository characterRepository;
    private final CharactersMapper charactersMapper;
    private final Random random = new Random();
    @Value("${rick.api.startPage}")
    private int startPage;
    @Value("${rick.api.endPage}")
    private int endPage;

    @Override
    public List<CharacterDto> saveAll(List<CharacterRequestDto> characterRequestDtoList) {
        return characterRepository.saveAll(characterRequestDtoList.stream()
                .map(charactersMapper::toModel)
                .toList()).stream()
                .map(charactersMapper::toDto).toList();
    }

    @Override
    public CharacterDto getRandom() {
        Long randomId = random.nextLong((endPage - startPage) * PAGE_SIZE);
        return charactersMapper.toDto(characterRepository.findById(randomId).orElseThrow(
                () -> new EntityNotFoundException("Cant find character with random id: "
                        + randomId)
        ));
    }

    @Override
    public List<CharacterDto> getListCharactersByName(String name) {
        return characterRepository.findCharacterByNameLikeIgnoreCase(name).stream()
                .map(charactersMapper::toDto)
                .toList();
    }
}
