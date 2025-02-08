package mate.academy.rickandmorty.services.rickandmortyclient;

import java.util.List;
import mate.academy.rickandmorty.dto.external.character.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.character.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.services.CharacterService;
import org.springframework.stereotype.Service;

@Service
public class CharacterDtoConvertService {

    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final CartoonClient client;
    private final CharacterService service;
    private final CharacterMapper mapper;

    public CharacterDtoConvertService(
            CartoonClient client,
            CharacterService service,
            CharacterMapper mapper) {
        this.client = client;
        this.service = service;
        this.mapper = mapper;
    }

    public void getCharacterConvertedDto() {
        List<CharacterInfoDto> characterInfoDto
                = client.fetch(URL, CharacterResponseDataDto.class).getResults();
        for (CharacterInfoDto character : characterInfoDto) {
            CartoonCharacter model = mapper.toModel(character);
            service.save(model);
        }
    }
}
