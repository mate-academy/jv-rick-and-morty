package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Override
    public void run(ApplicationArguments args) {
        int pagesQuantity = rickAndMortyClient.getPage(1).getInfo().getPages();
        List<Character> characters = new ArrayList<>();
        for (int i = 1; i <= pagesQuantity; i++) {
            characters.addAll(
                    rickAndMortyClient.getPage(i).getResults().stream()
                            .map(characterMapper::toModel)
                            .toList()
            );
        }
        characterRepository.saveAll(characters);
    }
}
