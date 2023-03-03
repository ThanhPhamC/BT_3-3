package demoOjt.model.sevice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService<T,V> {
    Page<T> getAll(Pageable pageable);
    T saveOrUpdate(T t);
    void delete(V id);
    T findById(V id);
    Page<T> findByName(String name, Pageable pageable);
}
