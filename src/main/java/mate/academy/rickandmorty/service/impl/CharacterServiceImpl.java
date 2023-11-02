package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterSearchParametersDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacter;
import mate.academy.rickandmorty.dto.internal.CharacterDataDto;
import mate.academy.rickandmorty.dto.internal.PageWIthCharacters;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.repository.character.CharacterSpecificationBuilder;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.ResourceService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String CHARACTER_NAME = "characters";
    private static final int DEFAULT_PAGE_NUMBER = 1;

    private final ResourceService<
            PageWIthCharacters,
            CharacterDataDto,
            CharacterSearchParametersDto> resourceService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterSpecificationBuilder characterSpecificationBuilder;

    @Override
    public ExternalCharacter getRandomCharacter() {
        int randomNumberCharacterId = new Random().nextInt(getNumbersOfCharacters());
        CharacterDataDto characterDto = resourceService.getDataFromId(
                CHARACTER_NAME,
                CharacterDataDto.class,
                randomNumberCharacterId);

        if (characterRepository.findByExternalId(characterDto.getId()).isPresent()) {
            return characterMapper.toDto(characterRepository.findByExternalId(
                    characterDto.getId()).get());
        }

        Character saveCharacter = characterRepository.save(characterMapper.toEntity(characterDto));
        return characterMapper.toDto(saveCharacter);
    }

    @Override
    public List<ExternalCharacter> searchCharacters(
            CharacterSearchParametersDto searchParametersDto, Pageable pageable
    ) {
        Specification<Character> characterSpecification =
                characterSpecificationBuilder.build(searchParametersDto);
        saveCharactersFromAllPagesToLocalDb(searchParametersDto);

        return characterRepository.findAll(characterSpecification, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    private void saveCharactersFromAllPagesToLocalDb(
            CharacterSearchParametersDto searchParametersDto
    ) {
        PageWIthCharacters currentPageData = getPageWithData(searchParametersDto);
        saveCharactersToLocalDb(currentPageData);

        while (currentPageData.getInfo().getNext() != null) {
            currentPageData = resourceService.getPageFromUrl(
                    currentPageData.getInfo().getNext(),
                    PageWIthCharacters.class
            );
            saveCharactersToLocalDb(currentPageData);
        }
    }

    private void saveCharactersToLocalDb(PageWIthCharacters dataFromSearchParam) {
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

    private PageWIthCharacters getPageWithData(CharacterSearchParametersDto searchParametersDto) {
        return resourceService.getDataFromSearchParam(
                searchParametersDto,
                PageWIthCharacters.class,
                CHARACTER_NAME);
    }

    private int getNumbersOfCharacters() {
        PageWIthCharacters pageWithCharacters = resourceService.getPageFromPageNumber(
                CHARACTER_NAME,
                PageWIthCharacters.class,
                DEFAULT_PAGE_NUMBER
        );
        return pageWithCharacters.getInfo().getCount();
    }

}
