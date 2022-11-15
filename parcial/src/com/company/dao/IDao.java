package com.company.dao;

import java.util.List;

public interface IDao<T> {
    public T guardar(T t);
    public List<T> buscarTodos();
    public void eliminar(Long id);
    public T buscar(Long id);

}
