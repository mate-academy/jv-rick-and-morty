package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpClient {
    private final ObjectMapper objectMapper;

    public <T> T get(String url, Class<? extends T> clazz) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        T resultValue = null;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                resultValue = objectMapper.readValue(result, clazz);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't execute get request", e);
        }
        return resultValue;
    }
}
