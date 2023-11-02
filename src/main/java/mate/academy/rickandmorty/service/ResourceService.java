package mate.academy.rickandmorty.service;

public interface ResourceService<R, C, S> {
    R getPageFromPageNumber(String resourceName, Class<R> className, int pageNumber);

    C getDataFromId(String resourceName, Class<C> className, int id);

    R getDataFromSearchParam(S searchParam, Class<R> className, String resourceName);

    R getPageFromUrl(String url, Class<R> className);
}
