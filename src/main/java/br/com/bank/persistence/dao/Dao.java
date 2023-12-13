package br.com.bank.persistence.dao;

import java.util.List;

public interface Dao<T> {

    T get(Long id);

    List<T>getAll();

    void save(T data);

    void update();

    void delete();

}
