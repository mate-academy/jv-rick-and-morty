package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.RickAndMortyClient;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.InfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterHandlerService {
    private final RickAndMortyClient client;
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterHandlerService(
            RickAndMortyClient client,
            CharacterRepository characterRepository
    ) {
        this.client = client;
        this.characterRepository = characterRepository;
    }

    @PostConstruct
    public void fetchAndStoreDataOnStartup() {
        InfoDto totalPagesInfo = getTotalPages();
        int totalPages = totalPagesInfo != null ? totalPagesInfo.pages() : 0;
        if (totalPages > 0) {
            List<CharacterInfoDto> allCharacters = new ArrayList<>();
            for (int page = 1; page <= totalPages; page++) {
                CharacterResponseDataDto response = client.getCharactersByPage(page);
                List<CharacterInfoDto> charactersOnPage =
                        response != null ? response.getResults() : Collections.emptyList();
                allCharacters.addAll(charactersOnPage);
            }
            List<Long> externalIds = allCharacters.stream()
                    .map(CharacterInfoDto::externalId)
                    .collect(Collectors.toList());
            List<CharacterEntity> existingCharacters = characterRepository
                    .findAllByExternalIdIn(externalIds);
            for (CharacterInfoDto characterInfoDto : allCharacters) {
                Optional<CharacterEntity> existingCharacter = existingCharacters.stream()
                        .filter(c -> c.getExternalId().equals(characterInfoDto.externalId()))
                        .findFirst();
                if (existingCharacter.isEmpty()) {
                    CharacterEntity characterEntity = new CharacterEntity();
                    characterEntity.setExternalId(characterInfoDto.externalId());
                    characterEntity.setName(characterInfoDto.name());
                    characterEntity.setStatus(characterInfoDto.status());
                    characterEntity.setGender(characterInfoDto.gender());
                    characterRepository.save(characterEntity);
                }
            }
        }
    }

    private InfoDto getTotalPages() {
        CharacterResponseDataDto firstPageData = client.getCharactersByPage(1);
        return firstPageData != null ? firstPageData.getInfo() : null;
    }

    public List<CharacterDto> getCharactersFromDatabase(String name) {
        List<CharacterEntity> characterEntities;
        if (name != null && !name.isEmpty()) {
            characterEntities = characterRepository.findByNameContainingIgnoreCase(name);
        } else {
            characterEntities = characterRepository.findAll();
        }
        return characterEntities.stream()
                .map(entity -> new CharacterDto(
                        entity.getId(),
                        entity.getExternalId(),
                        entity.getName(),
                        entity.getStatus(),
                        entity.getGender()
                ))
                .collect(Collectors.toList());
    }

    public CharacterDto getRandomCharacter() {
        List<CharacterEntity> allCharacters = characterRepository.findAll();
        if (allCharacters.isEmpty()) {
            return null;
        }
        int randomIndex = new Random().nextInt(allCharacters.size());
        CharacterEntity randomCharacterEntity = allCharacters.get(randomIndex);
        return new CharacterDto(
                randomCharacterEntity.getId(),
                randomCharacterEntity.getExternalId(),
                randomCharacterEntity.getName(),
                randomCharacterEntity.getStatus(),
                randomCharacterEntity.getGender()
        );
    }
}
