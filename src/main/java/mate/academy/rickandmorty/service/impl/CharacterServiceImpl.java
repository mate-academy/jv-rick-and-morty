package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterSearchParametersDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacter;
import mate.academy.rickandmorty.dto.internal.PageWIthInfoAdnCharacters;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.repository.character.CharacterSpecificationBuilder;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.ClientService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String CHARACTER_NAME = "characters";
    private final ClientService<PageWIthInfoAdnCharacters> clientService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterSpecificationBuilder characterSpecificationBuilder;
    private int numberOfCharacters;

    @Override
    public ExternalCharacter getRandomCharacter() {
        Long randomNumberCharacterId = new Random().nextLong(numberOfCharacters);

        Character character = characterRepository.findByExternalId(randomNumberCharacterId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find character by id: "
                        + randomNumberCharacterId));
        return characterMapper.toDto(character);
    }

    @Override
    public List<ExternalCharacter> searchCharacters(
            CharacterSearchParametersDto searchParametersDto, Pageable pageable
    ) {
        Specification<Character> characterSpecification =
                characterSpecificationBuilder.build(searchParametersDto);

        return characterRepository.findAll(characterSpecification, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @PostConstruct
    private void saveCharactersFromAllPagesToLocalDb() {
        PageWIthInfoAdnCharacters currentPageData = clientService.getPageFromResourceName(
                CHARACTER_NAME,
                PageWIthInfoAdnCharacters.class
        );
        numberOfCharacters = currentPageData.getInfo().getCount();
        saveCharactersToLocalDb(currentPageData);

        while (currentPageData.getInfo().getNext() != null) {
            currentPageData = clientService.getPageFromUrl(
                    currentPageData.getInfo().getNext(),
                    PageWIthInfoAdnCharacters.class
            );
            saveCharactersToLocalDb(currentPageData);
        }
    }

    private void saveCharactersToLocalDb(PageWIthInfoAdnCharacters dataFromSearchParam) {
        dataFromSearchParam.getResults().stream()
                .map(characterMapper::toEntity)
                .forEach(character -> {
                    if (!characterRepository.findByExternalId(
                            character.getExternalId()).isPresent()
                    ) {
                        characterRepository.save(character);
                    }
                });
    }
}
