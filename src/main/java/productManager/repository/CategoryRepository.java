package productManager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import productManager.model.Category;

// add Generic target class and id dataType
@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
