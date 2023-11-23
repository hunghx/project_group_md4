package ra.academy.dao;

import java.util.List;

public interface IGenericDao <T,E>{
    List<T> findAll(int limit, int offset);
    List<T> findAll();
    T findById(E id);
    int save(T t);
    int delete(E id);
}
