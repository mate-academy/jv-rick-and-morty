package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.RickAndMortyClient;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterHandlerService {
    private final RickAndMortyClient client;
    private final CharacterRepository characterRepository;

    public CharacterHandlerService(
            RickAndMortyClient client,
            CharacterRepository characterRepository
    ) {
        this.client = client;
        this.characterRepository = characterRepository;
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
