package com.bilgeadam.banket.utility;

import java.util.List;
import java.util.Optional;

public interface IService<T,ID> {

    T save(T t);

    Iterable<T> saveAll(Iterable<T> t);

    T update(T t);

    Boolean softDelete(T t);

    void hardDelete(T t);

    Boolean softDeleteById(ID id);

    void hardDeleteById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

    Boolean existsById(ID id);

}