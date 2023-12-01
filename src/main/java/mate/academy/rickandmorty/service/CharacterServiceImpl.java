package mate.academy.rickandmorty.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2
@Service
public class CharacterServiceImpl implements CharacterService {
    private final ApiService apiService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;

    @Override
    public void loadCharactersFromExternalApi() {
        log.info("syncExternalCharacter method was invoked at " + LocalDateTime.now());
        ApiResponseDto apiResponseDto = apiService.get("https://rickandmortyapi.com/api/character",
                ApiResponseDto.class);
        saveDtosToDb(apiResponseDto);

        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = apiService.get(apiResponseDto.getInfo().getNext(),
                    ApiResponseDto.class);
            saveDtosToDb(apiResponseDto);
        }
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long count = characterRepository.count();
        long randomId = (long) (Math.random() * count);
        return mapper.toDto(characterRepository.getReferenceById(randomId));
    }

    @Override
    public List<CharacterResponseDto> findAllByNameContains(String namePart) {
        return characterRepository.findAllByNameContains(namePart)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    private void saveDtosToDb(ApiResponseDto responseDto) {
        List<Character> charactersToSave = Arrays.stream(responseDto.getResults())
                .map(mapper::toModel)
                .collect(Collectors.toList());
        characterRepository.saveAll(charactersToSave);
    }
}
