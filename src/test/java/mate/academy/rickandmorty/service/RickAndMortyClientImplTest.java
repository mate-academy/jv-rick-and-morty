package mate.academy.rickandmorty.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;
import mate.academy.rickandmorty.dto.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.service.impl.RickAndMortyClientImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

class RickAndMortyClientImplTest {
    private static final String FIRST_PAGE = "https://example.test/character";
    private static final String SECOND_PAGE = "https://example.test/character?page=2";

    @Test
    void getAllCharacters_twoPages_returnsCharactersFromBothPages() {
        RestTemplate restTemplate = new RestTemplate();
        MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
        RickAndMortyClient client = new RickAndMortyClientImpl(restTemplate, FIRST_PAGE);

        server.expect(requestTo(FIRST_PAGE)).andRespond(withSuccess("""
                {
                  "info": {"next": "https://example.test/character?page=2"},
                  "results": [
                    {"id": 1, "name": "Rick Sanchez", "status": "Alive",
                     "gender": "Male"}
                  ]
                }
                """, MediaType.APPLICATION_JSON));
        server.expect(requestTo(SECOND_PAGE)).andRespond(withSuccess("""
                {
                  "info": {"next": null},
                  "results": [
                    {"id": 2, "name": "Morty Smith", "status": "Alive",
                     "gender": "Male"}
                  ]
                }
                """, MediaType.APPLICATION_JSON));

        List<RickAndMortyCharacterDto> actual = client.getAllCharacters();

        assertThat(actual).extracting(RickAndMortyCharacterDto::name)
                .containsExactly("Rick Sanchez", "Morty Smith");
        server.verify();
    }
}
