package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterDataLoader {
    private final CharacterRepository characterRepository;
    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;

    @EventListener(ApplicationReadyEvent.class)
    public void initDatabase() {
        characterClient.getCharacters()
                .stream()
                .map(characterMapper::toModel)
                .forEach(characterRepository::save);
    }
}
