package mate.academy.rickandmorty.service;

import java.util.List;

public interface DataFetchingService<T> {
    List<T> fetchAllData();
}
