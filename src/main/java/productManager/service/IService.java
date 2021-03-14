package productManager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import productManager.exception.PageNotFoundException;


import java.util.List;

public interface IService<T> {

    List<T> findALl();

    Page<T> findALl(Pageable pageable);

    T findById(Long id) throws PageNotFoundException;

    T save(T t);

    void deleteById(Long id);

    List<T> findByName(String name);
}
