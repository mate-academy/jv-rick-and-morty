package mate.academy.rickandmorty.repository;

public interface SpecProvManager<T> {
    SpecProvider<T> getSpecProvider(String key);
}
