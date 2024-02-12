package mate.academy.rickandmorty.initializer;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.initializer.charactersinit.CharactersInit;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements InitializingBean {
    private final CharactersInit charactersInit;

    @Override
    public void afterPropertiesSet() {
        charactersInit.init();
    }
}
