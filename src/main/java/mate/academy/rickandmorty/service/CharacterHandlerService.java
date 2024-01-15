package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        List<CharacterInfoDto> allCharacters = new ArrayList<>();

        int totalPages = getTotalPages();
        for (int page = 1; page <= totalPages; page++) {
            CharacterResponseDataDto response = client.getCharactersByPage(page);
            List<CharacterInfoDto> charactersOnPage =
                    response != null ? response.getResults() : Collections.emptyList();
            allCharacters.addAll(charactersOnPage);
        }

        for (CharacterInfoDto characterInfoDto : allCharacters) {
            Optional<CharacterEntity> existingCharacter = characterRepository
                    .findByExternalId(characterInfoDto.externalId());

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

    private int getTotalPages() {
        CharacterResponseDataDto characterResponseData = client.getCharactersByPage(1);
        InfoDto infoDto = characterResponseData != null ? characterResponseData.getInfo() : null;

        return infoDto != null ? infoDto.pages() : 0;
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

    public List<CharacterDto> getCharacter(String name) {
        List<CharacterInfoDto> characters = client.getCharacters(name);

        return characters.stream().map(c -> {
            Optional<CharacterEntity> existingCharacter = characterRepository
                    .findByExternalId(c.externalId());

            if (existingCharacter.isPresent()) {
                return new CharacterDto(
                        existingCharacter.get().getId(),
                        existingCharacter.get().getExternalId(),
                        existingCharacter.get().getName(),
                        existingCharacter.get().getStatus(),
                        existingCharacter.get().getGender()
                );
            }

            CharacterEntity characterEntity = new CharacterEntity();
            characterEntity.setExternalId(c.externalId());
            characterEntity.setName(c.name());
            characterEntity.setStatus(c.status());
            characterEntity.setGender(c.gender());

            characterEntity = characterRepository.save(characterEntity);

            return new CharacterDto(
                    characterEntity.getId(),
                    characterEntity.getExternalId(),
                    characterEntity.getName(),
                    characterEntity.getStatus(),
                    characterEntity.getGender()
            );
        }).toList();
    }
}
