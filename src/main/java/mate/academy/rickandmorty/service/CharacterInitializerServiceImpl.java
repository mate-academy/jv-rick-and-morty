package mate.academy.rickandmorty.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.component.CharacterClient;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.exeption.MalformedUrlException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterModel;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CharacterInitializerServiceImpl implements CharacterInitializerService {
    private final CharacterClient client;
    private final CharacterMapper characterMapper;
    private final CharacterRepository repository;

    @Transactional
    @Override
    public void initializeCharacters(String baseUrl) {
        List<CharacterModel> allCharacters = new ArrayList<>();
        String nextPageUrl = baseUrl;
        do {
            if (nextPageUrl != null && !nextPageUrl.isEmpty()) {
                try {
                    URI.create(nextPageUrl);
                } catch (IllegalArgumentException e) {
                    throw new MalformedUrlException("Malformed URL in nextPageUrl: "
                            + nextPageUrl, e);
                }
            } else {
                break;
            }
            CharacterResponseDataDto responseData = client.getAllCharacters(nextPageUrl);
            allCharacters.addAll(characterMapper.toCharacterModels(responseData.getResults()));
            nextPageUrl = responseData.getInfo().next();
        } while (nextPageUrl != null);
        repository.saveAll(allCharacters);
    }
}
