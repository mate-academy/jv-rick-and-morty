package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.DataLoader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoaderImpl implements DataLoader {
    private final CharacterRepository repository;

    private final CharacterClient characterClient;

    @Override
    public void downloadAndInsertDataIntoDb() {
        if (!isDatabaseUpToDate()) {
            List<Character> allCharacters = characterClient.getAllCharacters();
            for (Character character : allCharacters) {
                saveCharacterIfMissing(character);
            }
        }
    }

    private boolean isDatabaseUpToDate() {
        return characterClient
                .getDataByPage(1)
                .getInfo()
                .getCount() == repository.count();
    }

    private void saveCharacterIfMissing(Character character) {
        if (!repository.findByExternalId(character.getExternalId()).isPresent()) {
            repository.save(character);
        }
    }
}
