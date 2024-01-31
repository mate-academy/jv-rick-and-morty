package mate.academy.rickandmorty.util;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.RickAndMortyApiDataService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private final RickAndMortyApiDataService rickAndMortyApiDataService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        rickAndMortyApiDataService.fetchDataAndSaveToDatabase();
    }
}
