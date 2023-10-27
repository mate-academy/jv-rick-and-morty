package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static int listSize;
    private static final Random RANDOM = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);

    @Override
    public CharacterResponseDto getRandomCharacter() {
        int id = RANDOM.nextInt(listSize) + 1;
        logger.info("Generating a random character with id: {}", id);
        CharacterEntity character = characterRepository.findById((long) id).orElseThrow(() ->
                new RuntimeException("Character was not found with id " + id));
        CharacterResponseDto characterResponse = mapper.toDto(character);
        logger.info("Random character generated: {}", characterResponse);
        return characterResponse;
    }

    @Override
    public void saveAll(List<CharacterResultDto> listDto) {
        List<CharacterEntity> characters = listDto.stream()
                .map(mapper::toModel)
                .toList();
        logger.info("Saving {} characters to the database.", characters.size());
        listSize = characters.size();
        characterRepository.saveAll(characters);
        logger.info("Characters saved successfully.");
    }

    @Override
    public List<CharacterResponseDto> findByName(String name) {
        logger.info("Searching for characters by name: {}", name);
        List<CharacterEntity> characters = characterRepository.findByNameContaining(name);
        List<CharacterResponseDto> result = characters.stream()
                .map(mapper::toDto)
                .toList();
        logger.info("Found {} characters matching the search criteria.", result.size());
        return result;
    }
}
