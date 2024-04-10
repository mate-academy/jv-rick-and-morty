package mate.academy.rickandmorty.service;

public interface GenericService<T> {
    T save(T entity);

    T getById(Long id);

    void update(T entity);

    void deleteById(Long id);
}
