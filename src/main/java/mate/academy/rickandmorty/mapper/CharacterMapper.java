package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.client.RickAndMortyClient.CharacterResponse;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    public RickAndMortyCharacter toModel(CharacterResponse r) {
        RickAndMortyCharacter e = new RickAndMortyCharacter();
        e.setExternalId(r.getId());
        e.setName(r.getName());
        e.setStatus(r.getStatus());
        e.setGender(r.getGender());
        return e;
    }

    public CharacterDto toDto(RickAndMortyCharacter e) {
        return new CharacterDto(
                e.getId(),
                e.getExternalId(),
                e.getName(),
                e.getStatus(),
                e.getGender()
        );
    }
}
