package mate.academy.rickandmorty.service.http;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface HttpResponseService {
    HttpResponse<?> getHttpResponse(String url) throws IOException, InterruptedException;
}
