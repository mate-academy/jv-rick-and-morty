package mate.academy.rickandmorty.service;

public interface ClientService<R> {
    R getPageFromResourceName(String resourceName, Class<R> className);

    R getPageFromUrl(String url, Class<R> className);
}
