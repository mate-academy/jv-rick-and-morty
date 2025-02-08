package mate.academy.rickandmorty.repository;

public interface SpecificationProviderManager<E> {

    SpecificationProvider<E> getSpecificationProvider(String key);
}
